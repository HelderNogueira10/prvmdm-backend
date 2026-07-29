package com.privguard.mdm.server.device_status;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.heartbeat.HeartbeatRequest;
import org.springframework.stereotype.Service;

@Service
public class DeviceStatusService {

    private DeviceStatusRepository mRepository;

    public DeviceStatusService(DeviceStatusRepository _repo) {

        this.mRepository = _repo;
    }

    public boolean updateStatus(HeartbeatRequest _request, DeviceAccountEntity _deviceAccount) {

        return false;
    }
}
