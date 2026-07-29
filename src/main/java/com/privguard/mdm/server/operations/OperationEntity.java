package com.privguard.mdm.server.operations;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="operations")
public class OperationEntity extends BaseEntity {
    
    @NotNull @Column(nullable=false) private String message;
    @NotNull @Enumerated(EnumType.STRING) private OperationStatus status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }
}
