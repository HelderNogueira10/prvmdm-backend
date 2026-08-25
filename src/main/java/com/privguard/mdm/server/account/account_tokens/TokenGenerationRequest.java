package com.privguard.mdm.server.account.account_tokens;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.account.AccountTypes;

import java.time.Instant;

public class TokenGenerationRequest {

    private String jti;
    private Instant expireAt;
    private Instant issuedAt;
    private String accountUuid;
    private AccountTypes accountType;

    public String getJti() { return jti; }
    public Instant getExpireAt() { return expireAt; }
    public Instant getIssuedAt() { return issuedAt; }
    public String getAccountUuid() { return accountUuid; }
    public AccountTypes getAccountType() { return accountType; }

    public void setJti(String _jti) { this.jti = _jti; }
    public void setExpireAt(Instant _expireAt) { this.expireAt = _expireAt; }
    public void setIssuedAt(Instant _issuedAt) { this.issuedAt = _issuedAt; }
    public void setAccountUuid(String _accountUuid) { this.accountUuid = _accountUuid; }
    public void setAccountType(AccountTypes _accountType) { this.accountType = _accountType; }
}
