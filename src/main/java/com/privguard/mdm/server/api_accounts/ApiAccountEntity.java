package com.privguard.mdm.server.api_accounts;

import java.time.LocalDateTime;

import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.user_accounts.UserAccountEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="api_accounts")
public class ApiAccountEntity extends BaseEntity {
    
    @NotNull private String key;
    @NotNull private LocalDateTime expireDate;
    @NotNull @OneToOne @JoinColumn(name="user_id") private UserAccountEntity userId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public UserAccountEntity getUserId() {
        return userId;
    }

    public void setUserId(UserAccountEntity userId) {
        this.userId = userId;
    }
}
