package com.privguard.mdm.server.device_status;

import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "device_status")
public class DeviceStatusEntity extends BaseEntity {

    @OneToOne @JoinColumn(name = "device_id") private DeviceAccountEntity device;

    @Column(nullable = false) private Integer batteryLevel;
    @Column(nullable = false) private Integer batteryTemperature;

    @Column(nullable = false) private boolean charging;
    @Column(nullable = false) private boolean screenOn;

    @Column(nullable = false) private Long uptime;
    @Column(nullable = false) private Long cpuUsage;
    @Column(nullable = false) private Long memoryUsed;
    @Column(nullable = false) private Long storageUsed;

    @Column(nullable = false) private String wifiSignal;
    @Column(nullable = false) private String networkType;
    @Column(nullable = false) private String lastInternalIP;
    @Column(nullable = false) private String lastExternalIP;

    public DeviceAccountEntity getDevice() { return device; }

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

    public void setDevice(DeviceAccountEntity _device) { this.device = _device; }

    public void setBatteryLevel(Integer _batteryLevel) { this.batteryLevel = _batteryLevel; }
    public void setBatteryTemperature(Integer _batteryTemperature) { this.batteryTemperature = _batteryTemperature; }

    public void setCharging(boolean _charging) { this.charging = _charging; }
    public void setScreenOn(boolean _screenOn) { this.screenOn = _screenOn; }

    public void setUptime(Long _uptime) { this.uptime = _uptime; }
    public void setCpuUsage(Long _cpuUsage) { this.cpuUsage = _cpuUsage; }
    public void setMemoryUsed(Long _memoryUsed) { this.memoryUsed = _memoryUsed; }
    public void setStorageUsed(long _storageUsed) { this.storageUsed = _storageUsed; }

    public void setWifiSignal(String _wifiSignal) { this.wifiSignal = _wifiSignal; }
    public void setNetworktType(String _networkType) { this.networkType = _networkType; }
    public void setLastInternalIP(String _lastInternalIP) { this.lastInternalIP = _lastInternalIP; }
    public void setLastExternalIP(String _lastExternalIP) { this.lastExternalIP = _lastExternalIP; }

}
