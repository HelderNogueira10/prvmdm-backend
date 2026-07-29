package com.privguard.mdm.server.heartbeat;

import com.privguard.mdm.server.device_status.DeviceStatusRequest;

import java.time.LocalDateTime;

public class HeartbeatRequest {

    private String updateDate;
    private DeviceStatusRequest deviceStatus;

    public String getUpdateDate() { return updateDate; }
    public DeviceStatusRequest getDeviceStatus() { return deviceStatus; }
}
