package com.privguard.mdm.server.device_inventory;

import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="device_inventories")
public class DeviceInventoryEntity extends BaseEntity {
    
    private String manufacturer;
    private String androidVersion;
    private String bootloaderVersion;
    private String codename;
    private String buildNumber;
    private String hostname;
    private String cpuName;
    private String internalIP;
    private String externalIP;
    private String kernelVersion;
    private String wifiMACAddress;
    private String bluetoothMACAddress;
    private String model;
    private String productName;
    private String sdkVersion;
    private String securityPatch;
    private String serialNumber;
    private String sim1Number;
    private String emei1Number;
    private String sim2Number;
    private String emei2Number;
    private String activeNetworkTransport;

    private Integer batteryPercentage;
    private byte cpuCoresCount;

    private short wifiSingnal;

    private Long memoryTotal;
    private Long memoryUsed;
    private Long memoryAvailable;
    private Long storageTotal;
    private Long storageUsed;
    private Long storageAvailable;
    private float temperature;
    private Long uptime;
    private float cpuUsage;

    private boolean isCharging;
    private boolean isScreenOn;
    private boolean isEncrypted;
    private boolean isPlayProtected;
    private boolean hasNFC;
    private boolean hasGPS;
    private boolean hasBT;
    private boolean hasAccel;
    private boolean gpsEnabled;
    private boolean wifiEnabled;
    private boolean bluetoothEnabled;

    @NotNull @OneToOne @JoinColumn(name="device_id") private DeviceAccountEntity device;

    public String getActiveNetworkTransport() {
        return activeNetworkTransport;
    }

    public void setActiveNetworkTransport(String activeNetworkTransport) {
        this.activeNetworkTransport = activeNetworkTransport;
    }

    public short getWifiSingnal() {
        return wifiSingnal;
    }

    public void setWifiSingnal(short wifiSingnal) {
        this.wifiSingnal = wifiSingnal;
    }

    public float getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void setCharging(boolean charging) {
        isCharging = charging;
    }

    public boolean isScreenOn() {
        return isScreenOn;
    }

    public void setScreenOn(boolean screenOn) {
        isScreenOn = screenOn;
    }

    public String getWifiMACAddress() {
        return wifiMACAddress;
    }

    public void setWifiMACAddress(String wifiMACAddress) {
        this.wifiMACAddress = wifiMACAddress;
    }

    public String getBluetoothMACAddress() {
        return bluetoothMACAddress;
    }

    public void setBluetoothMACAddress(String bluetoothMACAddress) {
        this.bluetoothMACAddress = bluetoothMACAddress;
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

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    public boolean isPlayProtected() {
        return isPlayProtected;
    }

    public void setPlayProtected(boolean playProtected) {
        isPlayProtected = playProtected;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getBootloaderVersion() {
        return bootloaderVersion;
    }

    public void setBootloaderVersion(String bootloaderVersion) {
        this.bootloaderVersion = bootloaderVersion;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getInternalIP() {
        return internalIP;
    }

    public void setInternalIP(String internalIP) {
        this.internalIP = internalIP;
    }

    public String getExternalIP() {
        return externalIP;
    }

    public void setExternalIP(String externalIP) {
        this.externalIP = externalIP;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public void setKernelVersion(String kernelVersion) {
        this.kernelVersion = kernelVersion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getSecurityPatch() {
        return securityPatch;
    }

    public void setSecurityPatch(String securityPatch) {
        this.securityPatch = securityPatch;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSim1Number() {
        return sim1Number;
    }

    public void setSim1Number(String sim1Number) {
        this.sim1Number = sim1Number;
    }

    public String getEmei1Number() {
        return emei1Number;
    }

    public void setEmei1Number(String emei1Number) {
        this.emei1Number = emei1Number;
    }

    public String getSim2Number() {
        return sim2Number;
    }

    public void setSim2Number(String sim2Number) {
        this.sim2Number = sim2Number;
    }

    public String getEmei2Number() {
        return emei2Number;
    }

    public void setEmei2Number(String emei2Number) {
        this.emei2Number = emei2Number;
    }

    public Integer getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(Integer batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public byte getCpuCoresCount() {
        return cpuCoresCount;
    }

    public void setCpuCoresCount(byte cpuCoresCount) {
        this.cpuCoresCount = cpuCoresCount;
    }

    public Long getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(Long memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public Long getMemoryAvailable() {
        return memoryAvailable;
    }

    public void setMemoryAvailable(Long memoryAvailable) {
        this.memoryAvailable = memoryAvailable;
    }

    public Long getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(Long storageTotal) {
        this.storageTotal = storageTotal;
    }

    public Long getStorageAvailable() {
        return storageAvailable;
    }

    public void setStorageAvailable(Long storageAvailable) {
        this.storageAvailable = storageAvailable;
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

    public boolean isIsEncrypted() {
        return isEncrypted;
    }

    public void setIsEncrypted(boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

    public boolean isIsPlayProtected() {
        return isPlayProtected;
    }

    public void setIsPlayProtected(boolean isPlayProtected) {
        this.isPlayProtected = isPlayProtected;
    }

    public boolean isHasNFC() {
        return hasNFC;
    }

    public void setHasNFC(boolean hasNFC) {
        this.hasNFC = hasNFC;
    }

    public boolean isHasGPS() {
        return hasGPS;
    }

    public void setHasGPS(boolean hasGPS) {
        this.hasGPS = hasGPS;
    }

    public boolean isHasBT() {
        return hasBT;
    }

    public void setHasBT(boolean hasBT) {
        this.hasBT = hasBT;
    }

    public boolean isHasAccel() {
        return hasAccel;
    }

    public void setHasAccel(boolean hasAccel) {
        this.hasAccel = hasAccel;
    }

    public DeviceAccountEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceAccountEntity device) {
        this.device = device;
    }
}
