package com.privguard.mdm.server.provisioning;

import jakarta.validation.constraints.NotNull;

public class ProvisioningRequest {
    
    @NotNull private String schemaName;
    @NotNull private String componentName;
    @NotNull private String downloadLocation;
    @NotNull private String signatureChecksum;

    @NotNull private String timezone;
    @NotNull private String locale;
    @NotNull private String organizationName;
    
    @NotNull private String wifiSSID;
    @NotNull private boolean wifiHidden;
    @NotNull private String wifiPassword;
    @NotNull private String wifiSecurity;

    @NotNull private boolean encryptDevice;
    @NotNull private boolean debloatDevice;

    public String getDownloadLocation() {
        return downloadLocation;
    }

    public void setDownloadLocation(String downloadLocation) {
        this.downloadLocation = downloadLocation;
    }

    public String getSignatureChecksum() {
        return signatureChecksum;
    }

    public void setSignatureChecksum(String signatureChecksum) {
        this.signatureChecksum = signatureChecksum;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getWifiSSID() {
        return wifiSSID;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public boolean isWifiHidden() {
        return wifiHidden;
    }

    public void setWifiHidden(boolean wifiHidden) {
        this.wifiHidden = wifiHidden;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public String getWifiSecurity() {
        return wifiSecurity;
    }

    public void setWifiSecurity(String wifiSecurity) {
        this.wifiSecurity = wifiSecurity;
    }

    public boolean isEncryptDevice() {
        return encryptDevice;
    }

    public void setEncryptDevice(boolean encryptDevice) {
        this.encryptDevice = encryptDevice;
    }

    public boolean isDebloatDevice() {
        return debloatDevice;
    }

    public void setDebloatDevice(boolean debloatDevice) {
        this.debloatDevice = !debloatDevice;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String name) {
        this.schemaName = name;
    }
}
