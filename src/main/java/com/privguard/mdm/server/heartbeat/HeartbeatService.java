package com.privguard.mdm.server.heartbeat;

import com.privguard.mdm.server.device_status.DeviceStatusEntity;
import com.privguard.mdm.server.device_status.DeviceStatusRepository;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.security.JwtService;
import org.springframework.stereotype.Service;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.account.AccountsRepository;
import com.privguard.mdm.server.command.CommandsRepository;
import com.privguard.mdm.server.command.CommandsService;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.operations.OperationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class HeartbeatService {

    private final DeviceStatusRepository deviceStatusRepository;
    private CommandsService commandsService;
    private CommandsRepository commandsRepository;
    private JwtService jwtService;
    private DeviceAccountsRepository devicesRepository;
    private AccountsRepository accountsRepository;
    private DeviceAccountsRepository deviceAccountsRepository;
    private HeartbeatRepository mRepository;

    public HeartbeatService(HeartbeatRepository _repo, CommandsRepository _commandsRepository, DeviceAccountsRepository _devicesRepository,
                            CommandsService _commandsService, AccountsRepository _AccountsRepository,
                            JwtService _jwtService, DeviceAccountsRepository _deviceAccountsRepository, DeviceStatusRepository deviceStatusRepository) {

        this.mRepository = _repo;
        this.jwtService = _jwtService;
        this.commandsService = _commandsService;
        this.devicesRepository = _devicesRepository;
        this.commandsRepository = _commandsRepository;
        this.accountsRepository = _AccountsRepository;
        this.deviceAccountsRepository = _deviceAccountsRepository;
        this.deviceStatusRepository = deviceStatusRepository;
    }

    public HeartbeatResponse onHeartbeatReceived(HeartbeatRequest _req, AuthenticatedAccount _authedAccount) {

        HeartbeatResponse response = new HeartbeatResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            DeviceAccountEntity deviceAccount = deviceAccountsRepository.findByAccount_Uuid(_authedAccount.getUuid())
                .orElseThrow(() -> new RuntimeException("invalid request"));

            deviceAccount.setLastSeen(LocalDateTime.now());
            deviceAccountsRepository.save(deviceAccount);

            response.setTimestamp(LocalDateTime.now().toString());
            response.setPendingCommands(commandsService.getPendingCommands(deviceAccount));
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Heartbeat Received And Saved!");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Hearbeat Exception: " + _e.getMessage());
        }

        return response;
    }
}
