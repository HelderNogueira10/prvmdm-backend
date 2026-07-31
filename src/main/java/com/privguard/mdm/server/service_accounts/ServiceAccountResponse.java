package com.privguard.mdm.server.service_accounts;

import com.privguard.mdm.server.operations.OperationResponse;

public class ServiceAccountResponse extends OperationResponse {

    private String accountUuid;
    private String name;
    private String username;
    private String secret;

    public String getAccountUuid() { return accountUuid; }

    public void setAccountUuid(String _uuid) { this.accountUuid = _uuid; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
