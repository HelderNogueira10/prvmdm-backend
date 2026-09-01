package com.privguard.mdm.server.device_inventory;

public class BasicDeviceInventoryRequest {

    private Integer batteryPercentage;
    private Long memoryUsed;
    private Long memoryAvailable;
    private Long storageUsed;
    private Long storageAvailable;
    private float temperature;
    private Long uptime;
    private float cpuUsage;
    private String activeNetworkTransport;

    private short wifiSignal;
    private boolean isScreenOn;
    private boolean isCharging;
    private boolean gpsEnabled;
    private boolean wifiEnabled;
    private boolean bluetoothEnabled;

    public float getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getActiveNetworkTransport() {
        return activeNetworkTransport;
    }

    public void setActiveNetworkTransport(String activeNetworkTransport) {
        this.activeNetworkTransport = activeNetworkTransport;
    }

    public short getWifiSignal() {
        return wifiSignal;
    }

    public void setWifiSignal(short wifiSignal) {
        this.wifiSignal = wifiSignal;
    }

    public boolean isScreenOn() {
        return isScreenOn;
    }

    public void setScreenOn(boolean screenOn) {
        isScreenOn = screenOn;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void setCharging(boolean charging) {
        isCharging = charging;
    }

    public Long getMemoryAvailable() {
        return memoryAvailable;
    }

    public void setMemoryAvailable(Long memoryAvailable) {
        this.memoryAvailable = memoryAvailable;
    }

    public Long getStorageAvailable() {
        return storageAvailable;
    }

    public void setStorageAvailable(Long storageAvailable) {
        this.storageAvailable = storageAvailable;
    }

    public Integer getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(Integer batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public Long getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Long memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Long getStorageUsed() {
        return storageUsed;
    }

    public void setStorageUsed(Long storageUsed) {
        this.storageUsed = storageUsed;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

    public boolean isGpsEnabled() {
        return gpsEnabled;
    }

    public void setGpsEnabled(boolean gpsEnabled) {
        this.gpsEnabled = gpsEnabled;
    }

    public boolean isWifiEnabled() {
        return wifiEnabled;
    }

    public void setWifiEnabled(boolean wifiEnabled) {
        this.wifiEnabled = wifiEnabled;
    }

    public boolean isBluetoothEnabled() {
        return bluetoothEnabled;
    }

    public void setBluetoothEnabled(boolean bluetoothEnabled) {
        this.bluetoothEnabled = bluetoothEnabled;
    }
}
