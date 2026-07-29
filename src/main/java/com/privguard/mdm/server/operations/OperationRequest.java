package com.privguard.mdm.server.operations;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class OperationRequest {
    
    @NotNull private String message;
    @NotNull @Enumerated private OperationStatus status;

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
