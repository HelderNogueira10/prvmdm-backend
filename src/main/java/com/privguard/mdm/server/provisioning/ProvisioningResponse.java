package com.privguard.mdm.server.provisioning;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProvisioningResponse {
    
    @JsonProperty("android.app.extra.PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME")
    private String componentName;

    @JsonProperty("android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION")
    private String downloadLocation;

    @JsonProperty("android.app.extra.PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM")
    private String signatureChecksum;

    @JsonProperty("android.app.extra.PROVISIONING_WIFI_SSID")
    private String wifiSSID;

    @JsonProperty("android.app.extra.PROVISIONING_WIFI_SECURITY_TYPE")
    private String wifiSecurity;

    @JsonProperty("android.app.extra.PROVISIONING_WIFI_PASSWORD")
    private String wifiPassword;

    @JsonProperty("android.app.extra.PROVISIONING_WIFI_HIDDEN")
    private boolean wifiHidden;

    @JsonProperty("android.app.extra.PROVISIONING_SKIP_ENCRYPTION")
    private boolean encrypted;

    @JsonProperty("android.app.extra.PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED")
     private boolean leaveAllSystemAppsEnabled;

    @JsonProperty("android.app.extra.PROVISIONING_ORGANIZATION_NAME")
     private String organizationName;

    @JsonProperty("android.app.extra.PROVISIONING_TIME_ZONE")
     private String timezone;

    @JsonProperty("android.app.extra.PROVISIONING_LOCALE")
    private String locale;

    @JsonProperty("android.app.extra.PROVISIONING_ADMIN_EXTRAS_BUNDLE")
    private ProvisioningExtrasResponse extras;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

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

    public String getWifiSSID() {
        return wifiSSID == null ? "" : wifiSSID;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public String getWifiSecurity() {
        return wifiSecurity == null ? "" : wifiSecurity;
    }

    public void setWifiSecurity(String wifiSecurity) {
        this.wifiSecurity = wifiSecurity;
    }

    public String getWifiPassword() {
        return wifiPassword == null ? "" : wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public boolean isWifiHidden() {
        return wifiHidden;
    }

    public void setWifiHidden(boolean wifiHidden) {
        this.wifiHidden = wifiHidden;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public boolean isLeaveAllSystemAppsEnabled() {
        return !leaveAllSystemAppsEnabled;
    }

    public void setLeaveAllSystemAppsEnabled(boolean leaveAllSystemAppsEnabled) {
        this.leaveAllSystemAppsEnabled = leaveAllSystemAppsEnabled;
    }

    public String getOrganizationName() {
        return organizationName == null ? "" : organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getTimezone() {
        return timezone == null ? "" : timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLocale() {
        return locale == null ? "" : locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public ProvisioningExtrasResponse getExtras() {
        return extras;
    }

    public void setExtras(ProvisioningExtrasResponse extras) {
        this.extras = extras;
    }
}
