package com.privguard.mdm.server.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.privguard.mdm.server.account.AccountsRepository;
import com.privguard.mdm.server.apps.AppEntity;
import com.privguard.mdm.server.apps.FetchBasicApplicationResponse;
import com.privguard.mdm.server.command_types.CommandTypeEntity;
import com.privguard.mdm.server.command_types.CommandTypeRepository;
import com.privguard.mdm.server.device_accounts.DeviceAccountsService;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.user_accounts.UserAccountRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;

@Service
public class CommandsService {

    private CommandsRepository mRepository;
    private final AccountsRepository accountsRepository;
    private DeviceAccountsRepository deviceAccountsRepository;
    private final CommandTypeRepository commandTypeRepository;

    public CommandsService(CommandsRepository _repo,
                           DeviceAccountsRepository _deviceAccountsRepository,
                           AccountsRepository accountsRepository,
                           CommandTypeRepository commandTypeRepository) {

        this.mRepository = _repo;
        this.accountsRepository = accountsRepository;
        this.commandTypeRepository = commandTypeRepository;
        this.deviceAccountsRepository = _deviceAccountsRepository;
    }

    public List<CommandResponse> getPendingCommands(DeviceAccountEntity device) {

        List<CommandEntity> pendingCommands = mRepository.findByDeviceIdAndStatus(device, CommandStatus.PENDING);
        System.out.println("PENDING COMMANDS: " + pendingCommands.size());
        List<CommandResponse> commandsResponse = new ArrayList<>();

        for(CommandEntity command : pendingCommands) {

            Long commandTimeout = commandTypeRepository.findById(command.getType().getId()).orElseThrow().getTimeout();
            CommandResponse commandResponse = new CommandResponse();

            commandResponse.setId(command.getId());
            commandResponse.setTimeout(commandTimeout);
            commandResponse.setPayload(command.getPayload());
            commandResponse.setCommandTypeId(command.getType().getId());
            commandResponse.setCommandType(command.getType().getType());
            commandResponse.setAccountUuid(device.getAccount().getUuid());
            commandResponse.setCommandStatus(command.getStatus());
            commandsResponse.add(commandResponse);
        }

        System.out.println("COMMANDS RESPONSE: " + commandsResponse.size() );
        return commandsResponse;
    }

    public GetPagedCommandsResponse getPagedCommands(int page, int limit, AuthenticatedAccount _account) {

        GetPagedCommandsResponse response = new GetPagedCommandsResponse();

        try {

            //check perms
            int pageNumber = Math.max(page - 1, 0);
            List<GetCommandResponse> responses = new ArrayList<>();
            List<CommandEntity> dbCommands = mRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNumber, limit));

            response.setPageSize(pageNumber);
            for(CommandEntity command : dbCommands) {

                Long commandTimeout = commandTypeRepository.findById(command.getType().getId()).orElseThrow().getTimeout();
                GetCommandResponse commandResponse = new GetCommandResponse();
                commandResponse.setId(command.getId());
                commandResponse.setTimeout(commandTimeout);
                commandResponse.setType(command.getType().getId());
                commandResponse.setStatus(command.getStatus());
                commandResponse.setTypeName(command.getType().getType());
                commandResponse.setPayload(command.getPayload());
                commandResponse.setTargetName(command.getDeviceId().getHostname());
                commandResponse.setCreatedAt(command.getCreatedAt().toString());
                commandResponse.setEndedAt(String.valueOf(Objects.requireNonNullElse(command.getEndedAt(), "Data Unavailable")));
                commandResponse.setRequesterName(command.getAccountId().getName());

                responses.add(commandResponse);
            }

            response.setCommands(responses);
            response.setPageSize(pageNumber);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("CommandsService->getPagedCommands: " + _e.getMessage());
        }

        return response;
    }

    public Long getCommandsCount() { return mRepository.count(); }
    public Long getFailedCommandsCount() { return mRepository.countByStatus(CommandStatus.FAILED); }
    public Long getRunningCommandsCount() { return mRepository.countByStatus(CommandStatus.RUNNING); }
    public Long getPendingCommandsCount() { return mRepository.countByStatus(CommandStatus.PENDING); }
    public Long getSuccessfullCommandsCount() { return mRepository.countByStatus(CommandStatus.SUCCESS); }

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

            DeviceAccountEntity deviceAccount = deviceAccountsRepository.findByAccount_Id(_request.getDeviceAccountId())
                    .orElseThrow(() -> new RuntimeException("target device account not found!"));

            CommandTypeEntity commandType = commandTypeRepository.findById(_request.getType()).orElseThrow(
                    () -> new RuntimeException("command type not found...."));

            CommandEntity command = new CommandEntity();
            command.setAccountId(account);
            command.setStatus(CommandStatus.PENDING);
            command.setPayload(_request.getPayload());
            command.setType(commandType);
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
