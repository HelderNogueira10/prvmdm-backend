package com.privguard.mdm.server.device_inventory;

public class FullDeviceInventoryRequest {

    private String manufacturer;
    private String bootloaderVersion;
    private String codename;
    private String hostname;
    private String cpuName;
    private String kernelVersion;
    private String model;
    private String productName;
    private String sdkVersion;
    private String serialNumber;
    private String emei1Number;
    private String emei2Number;

    private byte cpuCoresCount;

    private Long memoryTotal;
    private Long storageTotal;

    private boolean isEncrypted;
    private boolean hasNFC;
    private boolean hasGPS;
    private boolean hasBT;
    private boolean hasAccel;

    private PartialDeviceInventoryRequest partialDevice;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEmei1Number() {
        return emei1Number;
    }

    public void setEmei1Number(String emei1Number) {
        this.emei1Number = emei1Number;
    }

    public String getEmei2Number() {
        return emei2Number;
    }

    public void setEmei2Number(String emei2Number) {
        this.emei2Number = emei2Number;
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

    public Long getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(Long storageTotal) {
        this.storageTotal = storageTotal;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
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

    public PartialDeviceInventoryRequest getPartialDevice() {
        return partialDevice;
    }

    public void setPartialDevice(PartialDeviceInventoryRequest partialDevice) {
        this.partialDevice = partialDevice;
    }
}
