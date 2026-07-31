package com.privguard.mdm.server.service_accounts;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "service_acocunts")
public class ServiceAccountEntity extends BaseEntity {
    
    @NotNull private String name;
    @NotNull private String secret;
    @NotNull @Column(nullable = false, unique = true) private String username;
    @NotNull @Enumerated(EnumType.STRING) private ServiceAccountStatus status;
    @NotNull @OneToOne @JoinColumn(name = "account_id") private AccountEntity account;

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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
