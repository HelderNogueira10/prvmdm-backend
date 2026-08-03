package com.privguard.mdm.server.api_accounts;

import java.time.LocalDateTime;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.user_accounts.UserAccountEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="api_accounts")
public class ApiAccountEntity extends BaseEntity {
    
    @NotNull private String key;
    @NotNull private LocalDateTime expireDate;
    @NotNull @Enumerated(EnumType.STRING) private ApiAccountStatus accountStatus;
    @NotNull @OneToOne @JoinColumn(name="account_id") private AccountEntity account;

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

    public ApiAccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(ApiAccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
