package com.privguard.mdm.server.user_accounts;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="user_accounts")
public class UserAccountEntity extends BaseEntity {
    
    @NotNull private String name;
    @Column(nullable=false, unique=true) private String email;
    @Column(nullable=false, unique=true) private String username;
    @NotNull private String password;
    @NotNull @Enumerated(EnumType.STRING) private UserAccountRoles role;
    @NotNull @OneToOne @JoinColumn(name = "account_id") private AccountEntity accountId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountEntity getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountEntity accountId) {
        this.accountId = accountId;
    }

    public UserAccountRoles getRole() {
        return role;
    }

    public void setRole(UserAccountRoles role) {
        this.role = role;
    }
}
