package com.privguard.mdm.server.inventory;

import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="inventories")
public class InventoryEntity extends BaseEntity {
    
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
    private String macAddress;
    private String model;
    private String productName;
    private String sdkVersion;
    private String securityPatch;
    private String serialNumber;
    private String sim1Number;
    private String emei1Number;
    private String sim2Number;
    private String emei2Number;

    private Integer batteryPercentage;
    private Integer cpuCoresCount;

    private Long memoryTotal;
    private Long memoryAvailable;
    private Long storageTotal;
    private Long storageAvailable;
    private Long temperature;
    private Long uptime;

    private boolean isEncrypted;
    private boolean isPlayProtected;
    private boolean hasNFC;
    private boolean hasGPS;
    private boolean hasBT;
    private boolean hasAccel;

    @NotNull @OneToOne @JoinColumn(name="device_id") private DeviceAccountEntity device;

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

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
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

    public Integer getCpuCoresCount() {
        return cpuCoresCount;
    }

    public void setCpuCoresCount(Integer cpuCoresCount) {
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

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
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
