package com.privguard.mdm.server.stats;

import com.privguard.mdm.server.command.CommandsService;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.enrollment.EnrollmentEntity;
import com.privguard.mdm.server.enrollment.EnrollmentRepository;
import com.privguard.mdm.server.enrollment.EnrollmentStatus;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatsService {

    private CommandsService commandsService;
    private EnrollmentRepository enrollmentsRepository;
    private DeviceAccountsRepository deviceAccountsRepository;

    public StatsService(DeviceAccountsRepository _deviceAccountsRepository,
                        CommandsService _commandsService,
                        EnrollmentRepository _enrollmentsRepository) {

        this.commandsService = _commandsService;
        this.enrollmentsRepository = _enrollmentsRepository;
        this.deviceAccountsRepository = _deviceAccountsRepository;
    }

    public CommandsStatsResponse getCommandStats(AuthenticatedAccount _account) {

        CommandsStatsResponse response = new CommandsStatsResponse();

        try {

            //check perms---
            response.setCommandsCount(commandsService.getCommandsCount());
            response.setFailedCommandsCount(commandsService.getFailedCommandsCount());
            response.setPendingCommandsCount(commandsService.getPendingCommandsCount());
            response.setRunningCommandsCount(commandsService.getRunningCommandsCount());
            response.setSuccessfullCommandsCount(commandsService.getSuccessfullCommandsCount());

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("StatsService->getCommandStats: " + _e.getMessage());
            _e.printStackTrace();
        }

        return response;
    }

    public DeviceStatsResponse getDeviceStats(AuthenticatedAccount _account) {

        DeviceStatsResponse response = new DeviceStatsResponse();

        try {

            //check permissions

            response.setAlertsCount(getAlertsCount());
            response.setOnlineDevices(getOnlineDevices());
            response.setOfflineDevices(getOfflineDevices());
            response.setPendingCommands(Integer.valueOf(commandsService.getPendingCommandsCount().toString()));

            response.setMessage("OK");
            response.setStatus(OperationStatus.SUCCESS);
        }
        catch (Exception _e) {

            response.setMessage("StatsService->getDeviceStats: " + _e.getMessage());
        }

        return response;
    }

    public ProvisioningStatsResponse getProvisionStats(AuthenticatedAccount _account) {

        ProvisioningStatsResponse response = new ProvisioningStatsResponse();

        try {

            //check perms
            EnrollmentEntity lastProvisioned = enrollmentsRepository.findAllByStatusOrderByCreatedAtDesc(EnrollmentStatus.ENROLLED).orElseThrow(
                    () -> new RuntimeException("last provisioned device exception..."));

            DeviceAccountEntity lastProvisionedDevice = lastProvisioned.getDevice();
            response.setLastProvisioned(lastProvisionedDevice.getCreatedAt().toString());
            response.setTotalFailed(enrollmentsRepository.findAllByStatus(EnrollmentStatus.FAILED).size());
            response.setTotalPending(enrollmentsRepository.findAllByStatus(EnrollmentStatus.PENDING).size());
            response.setTotalRevoked(enrollmentsRepository.findAllByStatus(EnrollmentStatus.RETIRED).size());
            response.setTotalProvisioned(enrollmentsRepository.findAllByStatus(EnrollmentStatus.ENROLLED).size());

            response.setMessage("OK");
            response.setStatus(OperationStatus.SUCCESS);
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("StatsService->getProvisionStats: " + _e.getMessage());
        }

        return response;
    }

    private int getOnlineDevices() {

        int onlineDevices = -1;

        try {

            LocalDateTime currentTimestamp = LocalDateTime.now();
            List<DeviceAccountEntity> devices = deviceAccountsRepository.findAll();
            for(DeviceAccountEntity deviceAccount : devices) {

                if(Duration.between(deviceAccount.getLastSeen(), currentTimestamp).toSeconds() <= 30)
                    onlineDevices++;
            }

            if(onlineDevices <= -1)
                onlineDevices = 0;
        }
        catch(Exception _e) { _e.printStackTrace(); }

        return onlineDevices;
    }

    private int getOfflineDevices() {

        int offlineDevices = -1;
        try {

           int totalDevices = deviceAccountsRepository.findAll().size();
            offlineDevices = totalDevices - getOnlineDevices();
        }
        catch (Exception _e) { _e.printStackTrace(); }

        return offlineDevices;
    }

    private int getAlertsCount() {

        return -1;
    }
}
