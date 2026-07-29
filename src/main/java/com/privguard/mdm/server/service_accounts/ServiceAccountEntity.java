package com.privguard.mdm.server.service_accounts;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class ServiceAccountEntity extends BaseEntity {
    
    @NotNull private String name;
    @NotNull private String secret;
    @NotNull private String username;
    @NotNull @Enumerated(EnumType.STRING) private ServiceAccountStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ServiceAccountStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceAccountStatus status) {
        this.status = status;
    }
}
