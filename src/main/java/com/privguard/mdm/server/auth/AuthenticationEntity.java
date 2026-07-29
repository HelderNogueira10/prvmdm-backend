package com.privguard.mdm.server.auth;

import java.time.LocalDateTime;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="authentications")
public class AuthenticationEntity extends BaseEntity {
    
    @NotNull @Enumerated private AuthenticationTypes type;
    @NotNull @Enumerated private AuthenticationStatus status;
    @NotNull private LocalDateTime timestamp;
    @NotNull private String description;
    @NotNull private String sourceIP;

    public AuthenticationTypes getType() {
        return type;
    }

    public void setType(AuthenticationTypes type) {
        this.type = type;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }
}
