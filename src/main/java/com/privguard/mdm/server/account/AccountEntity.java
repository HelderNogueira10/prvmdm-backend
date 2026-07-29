package com.privguard.mdm.server.account;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="accounts")
public class AccountEntity extends BaseEntity {
 
    @Column(nullable = false, unique = true) private String uuid;
    @NotNull @Enumerated(EnumType.STRING) private AccountTypes type;
    @NotNull @Enumerated(EnumType.STRING) private AccountStatus status;

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public AccountTypes getType() {
        return type;
    }

    public void setType(AccountTypes type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    
}
