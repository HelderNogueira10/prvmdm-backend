package com.privguard.mdm.server.provisioning;

public class GetProvisioningSchemaResponse {

    private Long id;
    private String locale;
    private String wifiSSID;
    private String timezone;
    private String craetedAt;
    private String updatedAt;
    private String schemaName;
    private String downloadUri;
    private String wifiSecurity;
    private String componentName;
    private String organizationName;

    private boolean encrypted;
    private boolean systemAppsInstalled;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getWifiSSID() {
        return wifiSSID;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCraetedAt() {
        return craetedAt;
    }

    public void setCraetedAt(String craetedAt) {
        this.craetedAt = craetedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getWifiSecurity() {
        return wifiSecurity;
    }

    public void setWifiSecurity(String wifiSecurity) {
        this.wifiSecurity = wifiSecurity;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public boolean isSystemAppsInstalled() {
        return systemAppsInstalled;
    }

    public void setSystemAppsInstalled(boolean systemAppsInstalled) {
        this.systemAppsInstalled = systemAppsInstalled;
    }
}
