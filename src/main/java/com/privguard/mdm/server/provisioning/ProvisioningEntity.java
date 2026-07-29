package com.privguard.mdm.server.provisioning;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="provisioning_schemas")
public class ProvisioningEntity extends BaseEntity {
    
    @Column(nullable=false, unique=true) private String schemaName;
    @Column(nullable=false) private String componentName;
    @Column(nullable=false) private String downloadLocation;
    @Column(nullable=false) private String signatureChecksum;

    private String timezone;
    private String locale;
    private String organizationName;
    
    private String wifiSSID;
    private boolean wifiHidden;
    private String wifiPassword;
    private String wifiSecurity;

    private boolean encryptDevice;
    private boolean leaveSystemApps;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String name) {
        this.schemaName = name;
    }

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

    public boolean isLeaveSystemApps() {
        return leaveSystemApps;
    }

    public void setLeaveSystemApps(boolean leaveSystemApps) {
        this.leaveSystemApps = leaveSystemApps;
    }
}
