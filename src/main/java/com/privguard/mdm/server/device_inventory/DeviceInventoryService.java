package com.privguard.mdm.server.device_inventory;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.exceptions.EntityNotFoundException;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.stereotype.Service;

@Service
public class DeviceInventoryService {

    private DeviceInventoryRepository mRepository;
    private DeviceAccountsRepository deviceAccountsRepository;

    public DeviceInventoryService(DeviceInventoryRepository _mRepository,
                                  DeviceAccountsRepository _deviceAccountsRepository) {

        this.mRepository = _mRepository;
        this.deviceAccountsRepository = _deviceAccountsRepository;
    }

    public OperationResponse updateBasic(BasicDeviceInventoryRequest _request, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();

        try {

            DeviceInventoryEntity dbInventory = findDeviceInventory(_account.getUuid(), _account);
            dbInventory.setUptime(_request.getUptime());
            dbInventory.setCharging(_request.isCharging());
            dbInventory.setScreenOn(_request.isScreenOn());
            dbInventory.setCpuUsage(_request.getCpuUsage());
            dbInventory.setGpsEnabled(_request.isGpsEnabled());
            dbInventory.setMemoryUsed(_request.getMemoryUsed());
            dbInventory.setWifiEnabled(_request.isWifiEnabled());
            dbInventory.setWifiSingnal(_request.getWifiSignal());
            dbInventory.setTemperature(_request.getTemperature());
            dbInventory.setStorageUsed(_request.getStorageUsed());
            dbInventory.setMemoryAvailable(_request.getMemoryAvailable());
            dbInventory.setStorageAvailable(_request.getStorageAvailable());
            dbInventory.setBluetoothEnabled(_request.isBluetoothEnabled());
            dbInventory.setBatteryPercentage(_request.getBatteryPercentage());
            dbInventory.setActiveNetworkTransport(_request.getActiveNetworkTransport());
            mRepository.save(dbInventory);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            _e.printStackTrace();
            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("DeviceInventoryService->updateBasic: " + _e.getMessage());
        }

        return response;
    }

    public OperationResponse updatePartial(PartialDeviceInventoryRequest _request, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();

        try {

            DeviceInventoryEntity dbInventory = findDeviceInventory(_account.getUuid(), _account);
            dbInventory.setInternalIP(_request.getInternalIP());
            dbInventory.setSim1Number(_request.getSim1Number());
            dbInventory.setBuildNumber(_request.getBuildNumber());
            dbInventory.setSecurityPatch(_request.getSecurityPatch());
            dbInventory.setAndroidVersion(_request.getAndroidVersion());

            updateBasic(_request.getBasicDevice(), _account);
            mRepository.save(dbInventory);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) {

            _e.printStackTrace();
            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("DeviceInventoryService->updatePartial: " + _e.getMessage());
        }

        return response;
    }

    public OperationResponse updateFull(FullDeviceInventoryRequest _request, AuthenticatedAccount _account) {

        OperationResponse response  = new OperationResponse();

        try {

            DeviceInventoryEntity dbInventory = findDeviceInventory(_account.getUuid(), _account);
            dbInventory.setHasBT(_request.isHasBT());
            dbInventory.setModel(_request.getModel());
            dbInventory.setHasGPS(_request.isHasGPS());
            dbInventory.setHasNFC(_request.isHasNFC());
            dbInventory.setCpuName(_request.getCpuName());
            dbInventory.setHasAccel(_request.isHasAccel());
            dbInventory.setCodename(_request.getCodename());
            dbInventory.setEncrypted(_request.isEncrypted());
            dbInventory.setSdkVersion(_request.getSdkVersion());
            dbInventory.setEmei1Number(_request.getEmei1Number());
            dbInventory.setProductName(_request.getProductName());
            dbInventory.setManufacturer(_request.getManufacturer());
            dbInventory.setBootloaderVersion(_request.getBootloaderVersion());
            dbInventory.setKernelVersion(_request.getKernelVersion());
            dbInventory.setSerialNumber(_request.getSerialNumber());
            dbInventory.setEmei2Number(_request.getEmei2Number());
            dbInventory.setCpuCoresCount(_request.getCpuCoresCount());
            dbInventory.setMemoryTotal(_request.getMemoryTotal());
            dbInventory.setStorageTotal(_request.getStorageTotal());

            updatePartial(_request.getPartialDevice(), _account);
            mRepository.save(dbInventory);
        }
        catch(Exception _e) {

            _e.printStackTrace();
            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("DeviceInventoryService->update: " + _e.getMessage());
        }

        return response;
    }

    private DeviceInventoryEntity findDeviceInventory(String _accountUuid, AuthenticatedAccount _account) {

        //TODO: check permissions
        DeviceInventoryEntity dbInventory = mRepository.findByDevice(deviceAccountsRepository.findByAccount_Uuid(_accountUuid).
                orElseThrow(EntityNotFoundException::new)).orElse(new DeviceInventoryEntity());

        DeviceAccountEntity dbDevice = deviceAccountsRepository.findByAccount_Uuid(_account.getUuid()).orElseThrow(
                EntityNotFoundException::new);
        dbInventory.setDevice(dbDevice);

        return dbInventory;
    }
}
