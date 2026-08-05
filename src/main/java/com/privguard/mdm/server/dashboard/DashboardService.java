package com.privguard.mdm.server.dashboard;

import com.privguard.mdm.server.apps.AppsService;
import com.privguard.mdm.server.command.CommandStatus;
import com.privguard.mdm.server.command.CommandsRepository;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardService {

    private final DeviceAccountsRepository deviceAccountsRepository;
    private final CommandsRepository commandsRepository;
    private final AppsService appsService;

    public DashboardService(DeviceAccountsRepository deviceAccountsRepository, CommandsRepository commandsRepository, AppsService appsService) {
        this.deviceAccountsRepository = deviceAccountsRepository;
        this.commandsRepository = commandsRepository;
        this.appsService = appsService;
    }

    public DashboardResponse getMainStats() {

        DashboardResponse response = new DashboardResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            int onlineDevices = 0;
            LocalDateTime currentTimestamp = LocalDateTime.now();
            List<DeviceAccountEntity> devices = deviceAccountsRepository.findAll();
            for(DeviceAccountEntity deviceAccount : devices) {

                if(Duration.between(deviceAccount.getLastSeen(), currentTimestamp).toSeconds() <= 30)
                    onlineDevices++;
            }

            response.setOnlineDevices(onlineDevices);
            response.setOfflineDevices(devices.size() - onlineDevices);
            response.setApplications(appsService.getTotalApplications());
            response.setPendingCommands(commandsRepository.findAllByStatus(CommandStatus.PENDING).size());

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Stats collected at " + LocalDateTime.now());
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Dashboard Main Stats Collection Exception: " + _e.getMessage());
        }

        return response;
    }
}
