package com.privguard.mdm.server.server_health;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "health_checks")
public class ServerHealthEntity extends BaseEntity {
    
    @Column(nullable = false) @Enumerated(EnumType.STRING) private ServerHealthStatus healthStatus;

    public ServerHealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(ServerHealthStatus status) {
        this.healthStatus = status;
    }
}
