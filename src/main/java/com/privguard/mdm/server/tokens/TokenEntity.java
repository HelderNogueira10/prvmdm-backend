package com.privguard.mdm.server.tokens;

import java.time.LocalDateTime;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tokens")
public class TokenEntity extends BaseEntity {
    
    @NotNull private String token;
    @NotNull private LocalDateTime startDate;
    @NotNull private LocalDateTime expireDate;
    @NotNull @Enumerated(EnumType.STRING) private TokenStatus status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public TokenStatus getStatus() {
        return status;
    }

    public void setStatus(TokenStatus status) {
        this.status = status;
    }
}
