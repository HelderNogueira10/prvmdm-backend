package com.privguard.mdm.server.device_accounts;

import com.privguard.mdm.server.account.AccountEntity;

import jakarta.validation.constraints.NotNull;

public class DeviceAccountRequest {
    
    @NotNull private String imei;
    @NotNull private String secret;
    @NotNull private String hostname;


	public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
