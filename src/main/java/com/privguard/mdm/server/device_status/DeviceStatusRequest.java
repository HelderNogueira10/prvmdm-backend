package com.privguard.mdm.server.device_status;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import jakarta.persistence.Column;

public class DeviceStatusRequest {

    private Integer batteryLevel;
    private Integer batteryTemperature;

    private boolean charging;
    private boolean screenOn;

    private Long uptime;
    private Long cpuUsage;
    private Long memoryUsed;
    private Long storageUsed;

    private String wifiSignal;
    private String networkType;
    private String lastInternalIP;
    private String lastExternalIP;

    public Integer getBatteryLevel() { return batteryLevel; }
    public Integer getBatteryTemperature() { return batteryTemperature; }

    public boolean isCharging() { return charging; }
    public boolean isScreenOn() { return screenOn; }

    public Long getUptime() { return uptime; }
    public Long getCpuUsage() { return cpuUsage; }
    public Long getMemoryUsed() { return memoryUsed; }
    public Long getStorageUsed() { return storageUsed; }

    public String getWifiSignal() { return wifiSignal; }
    public String getNetworkType() { return networkType; }
    public String getLastInternalIP() { return lastInternalIP; }
    public String getLastExternalIP() { return lastExternalIP; }
}
