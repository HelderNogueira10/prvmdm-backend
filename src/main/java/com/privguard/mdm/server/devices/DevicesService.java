package com.privguard.mdm.server.devices;

import com.privguard.mdm.server.app_files.AppFileEntity;
import com.privguard.mdm.server.app_versions.AppVersionEntity;
import com.privguard.mdm.server.apps.AppEntity;
import com.privguard.mdm.server.apps.FetchBasicApplicationResponse;
import com.privguard.mdm.server.command.CommandStatus;
import com.privguard.mdm.server.command.CommandsRepository;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.device_status.DeviceStatusEntity;
import com.privguard.mdm.server.device_status.DeviceStatusRepository;
import com.privguard.mdm.server.inventory.DeviceInventoryRepository;
import com.privguard.mdm.server.inventory.InventoryEntity;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DevicesService {

    private final DeviceInventoryRepository deviceInventoryRepository;
    private final DeviceStatusRepository deviceStatusRepository;
    private final DeviceAccountsRepository deviceAccountsRepository;

    private final CommandsRepository commandsRepository;

    public DevicesService(DeviceStatusRepository deviceStatusRepository,
                          DeviceAccountsRepository deviceAccountsRepository,
                          CommandsRepository commandsRepository,
                          DeviceInventoryRepository _deviceInventoryRespository) {

        this.deviceStatusRepository = deviceStatusRepository;
        this.deviceAccountsRepository = deviceAccountsRepository;
        this.commandsRepository = commandsRepository;
        this.deviceInventoryRepository = _deviceInventoryRespository;
    }

    public FetchBaseDeviceResponse getBase(String uuid, AuthenticatedAccount _account) {

        FetchBaseDeviceResponse response = new FetchBaseDeviceResponse();

        try {

            //check perms

            DeviceAccountEntity dbDeviceAccount = deviceAccountsRepository.findByAccount_Uuid(uuid).orElseThrow(
                    () -> new RuntimeException("device not found"));

            DeviceStatusEntity dbDeviceStatus = deviceStatusRepository.findByDevice(dbDeviceAccount).orElseThrow(
                    () -> new RuntimeException("device status not found"));

            response.setId(dbDeviceAccount.getId());
            response.setHostname("NA");
            response.setBatteryLevel(dbDeviceStatus.getBatteryLevel());
            response.setImei(dbDeviceAccount.getImei());
            response.setModel("NA");
            response.setLastHeartbeat(dbDeviceAccount.getLastSeen().toString());
            response.setPendingCommands(commandsRepository.findAllByDeviceIdAndStatus(dbDeviceAccount, CommandStatus.PENDING).size());

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) { response.setMessage("DevicesService->getBase:  " + _e.getMessage()); }

        return response;
    }

    public FetchBaseDevicesResponse getBaseDevices(int _pageId, int _count, AuthenticatedAccount _account) {

        FetchBaseDevicesResponse response = new FetchBaseDevicesResponse();

        try {

            //check perms
            int pageNumber = Math.max(_pageId - 1, 0);
            List<FetchBaseDeviceResponse> devicesList = new ArrayList<>();
            List<DeviceAccountEntity> dbDeviceAccounts = deviceAccountsRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNumber, _count));

            for(DeviceAccountEntity device : dbDeviceAccounts) {

                DeviceStatusEntity deviceStatus = deviceStatusRepository.findByDevice(device).orElseThrow(
                        () -> new RuntimeException("device not found!"));

                InventoryEntity deviceInventory = deviceInventoryRepository.findByDevice(device).orElseThrow(
                        () -> new RuntimeException("device inventory not found..."));

                FetchBaseDeviceResponse deviceResponse = new FetchBaseDeviceResponse();
                deviceResponse.setLastHeartbeat(device.getLastSeen().toString());
                deviceResponse.setMessage("OK");
                deviceResponse.setHostname("NA");
                deviceResponse.setDeviceStatus("ok");
                deviceResponse.setId(device.getId());
                deviceResponse.setImei(device.getImei());
                deviceResponse.setStatus(OperationStatus.SUCCESS);
                deviceResponse.setModel(deviceInventory.getModel());
                deviceResponse.setScreenOn(deviceResponse.isScreenOn());
                deviceResponse.setBatteryLevel(deviceStatus.getBatteryLevel());
                deviceResponse.setTemperature(deviceStatus.getBatteryTemperature());
                deviceResponse.setPendingCommands(commandsRepository.findAllByDeviceIdAndStatus(device, CommandStatus.PENDING).size());
                devicesList.add(deviceResponse);
            }

            response.setMessage("OK");
            response.setDevices(devicesList);
            response.setStatus(OperationStatus.SUCCESS);
            response.setTotalCount(deviceAccountsRepository.count());
        }
        catch (Exception _e) {

            _e.printStackTrace();
            response.setMessage("AppsService->getPaged: " + _e.getMessage());
        }

        return response;
    }

    private FetchRecentDevicesResponse getRecentDevices(int _count, AuthenticatedAccount _account) {

        FetchRecentDevicesResponse response = new FetchRecentDevicesResponse();

        try {

            //chekc perms

            List<DeviceAccountEntity> devicesList = deviceAccountsRepository.findAll();

            for(int a = 0; a < _count; a++) {
                for(int n = 0; n < devicesList.size(); n++) {


                }
            }
        }
        catch (Exception _e) {

            response.setMessage("DevicesServices->getRecentDevices: " + _e.getMessage());
            _e.printStackTrace();
        }

        return response;
    }
}
