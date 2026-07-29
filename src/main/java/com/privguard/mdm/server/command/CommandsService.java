package com.privguard.mdm.server.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.privguard.mdm.server.account.AccountsRepository;
import com.privguard.mdm.server.device_accounts.DeviceAccountsService;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.stereotype.Service;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;

@Service
public class CommandsService {

    private final AccountsRepository accountsRepository;
    //private DeviceRepositor
    private DeviceAccountsService deviceAccountsService;
    private CommandsRepository mRepository;
    private DeviceAccountsRepository deviceAccountsRepository;

    public CommandsService(CommandsRepository _repo, DeviceAccountsService _agentsService,
                           DeviceAccountsRepository _deviceAccountsRepository, AccountsRepository accountsRepository) {

        this.mRepository = _repo;
        this.deviceAccountsService = _agentsService;
        this.deviceAccountsRepository = _deviceAccountsRepository;
        this.accountsRepository = accountsRepository;
    }

    public List<CommandResponse> getPendingCommands(DeviceAccountEntity device) {

        List<CommandEntity> pendingCommands = mRepository.findByDeviceIdAndStatus(device, CommandStatus.PENDING);
        System.out.println("PENDING COMMANDS: " + pendingCommands.size());
        List<CommandResponse> commandsResponse = new ArrayList<>();

        for(CommandEntity command : pendingCommands) {

            CommandResponse commandResponse = new CommandResponse();
            commandResponse.setId(command.getId());
            commandResponse.setPayload(command.getPayload());
            commandResponse.setCommandType(command.getType());
            commandResponse.setAccountUuid(device.getAccount().getUuid());
            commandResponse.setCommandStatus(command.getStatus());
            commandsResponse.add(commandResponse);
        }

        System.out.println("COMMANDS RESPONSE: " + commandsResponse.size() );
        return commandsResponse;
    }

    public OperationResponse updateCommand(CommandUpdateRequest _request, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            CommandEntity command = mRepository.findById(_request.getId())
                    .orElseThrow(() -> new RuntimeException("command does not exist"));

            if(!command.getDeviceId().getAccount().getUuid().equals(_account.getUuid()))
                throw new RuntimeException("this command is not destined for your device");

            if(_request.getStatus() == CommandStatus.RUNNING)
                command.setStartedAt(LocalDateTime.now());

            if(_request.getStatus() == CommandStatus.SUCCESS || _request.getStatus() == CommandStatus.FAILED)
                command.setEndedAt(LocalDateTime.now());

            command.setStatus(_request.getStatus());
            mRepository.save(command);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Command Status Updated Successfully");
        }
        catch(Exception _e) {

            response.setMessage("Update Command Exception: " + _e.getMessage());
            response.setStatus(OperationStatus.FAILURE);
        }

        return response;
    }

    public OperationResponse addCommand(CommandRequest _request, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            AccountEntity account = accountsRepository.findByUuid(_account.getUuid())
                    .orElseThrow(() -> new RuntimeException("account is not valid!"));

            DeviceAccountEntity deviceAccount = deviceAccountsRepository.findByAccount_Uuid(_request.getDeviceAccountUuid())
                    .orElseThrow(() -> new RuntimeException("target device account not found!"));

            CommandEntity command = new CommandEntity();
            command.setAccountId(account);
            command.setStatus(CommandStatus.PENDING);
            command.setPayload(_request.getPayload());
            command.setType(_request.getType());
            command.setDeviceId(deviceAccount);
            mRepository.save(command);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Command Added Successfully: " + command.getId());
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Add Command Exception: " + _e.getMessage());
        }

        return response;
    }
}
