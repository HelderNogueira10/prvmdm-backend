package com.privguard.mdm.server.account.account_tokens;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.tokens.TokenStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_tokens")
public class AccountTokenEntity extends BaseEntity {

    @Column(nullable = false) @Enumerated(EnumType.STRING) private TokenStatus status;
    @Column(nullable = false) private LocalDateTime expireAt;
    @Column(nullable = false, unique = true) private String jti;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "account_id") private AccountEntity account;

    public String getJti() { return jti; }
    public TokenStatus getStatus() { return status; }
    public AccountEntity getAccount() { return account; }
    public LocalDateTime getExpireAt() { return expireAt; }

    public void setJti(String _jti) { this.jti = _jti; }
    public void setStatus(TokenStatus _status) { this.status = _status; }
    public void setExpireAt(LocalDateTime _expireAt) { this.expireAt = _expireAt; }
    public void setAccount(AccountEntity _accountEntity) { this.account = _accountEntity; }
}
