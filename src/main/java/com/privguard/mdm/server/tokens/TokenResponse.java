package com.privguard.mdm.server.tokens;

import com.privguard.mdm.server.operations.OperationResponse;

import jakarta.validation.constraints.NotNull;

public class TokenResponse extends OperationResponse {
    
    @NotNull private String name;
    @NotNull private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
