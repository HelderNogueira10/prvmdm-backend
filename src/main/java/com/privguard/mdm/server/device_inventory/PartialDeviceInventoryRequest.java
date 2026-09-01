package com.privguard.mdm.server.device_inventory;

public class PartialDeviceInventoryRequest {

    private String deviceUuid;
    private String androidVersion;
    private String buildNumber;
    private String internalIP;
    private String securityPatch;
    private String sim1Number;

    private BasicDeviceInventoryRequest basicDevice;

    private boolean isPlayProtected;

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getInternalIP() {
        return internalIP;
    }

    public void setInternalIP(String internalIP) {
        this.internalIP = internalIP;
    }

    public String getSecurityPatch() {
        return securityPatch;
    }

    public void setSecurityPatch(String securityPatch) {
        this.securityPatch = securityPatch;
    }

    public String getSim1Number() {
        return sim1Number;
    }

    public void setSim1Number(String sim1Number) {
        this.sim1Number = sim1Number;
    }

    public boolean isPlayProtected() {
        return isPlayProtected;
    }

    public void setPlayProtected(boolean playProtected) {
        isPlayProtected = playProtected;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public BasicDeviceInventoryRequest getBasicDevice() {
        return basicDevice;
    }

    public void setBasicDevice(BasicDeviceInventoryRequest basicDevice) {
        this.basicDevice = basicDevice;
    }
}
