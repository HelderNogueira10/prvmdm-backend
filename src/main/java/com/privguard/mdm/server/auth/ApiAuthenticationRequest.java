package com.privguard.mdm.server.auth;

import jakarta.validation.constraints.NotNull;

public class ApiAuthenticationRequest {

    @NotNull private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
