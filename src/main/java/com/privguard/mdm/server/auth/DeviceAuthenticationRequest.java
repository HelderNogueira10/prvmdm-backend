package com.privguard.mdm.server.auth;

public class DeviceAuthenticationRequest {
    
    private String accountUuid;
    private String secret;

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


}