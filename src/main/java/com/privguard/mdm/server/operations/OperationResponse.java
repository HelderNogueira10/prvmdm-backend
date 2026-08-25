package com.privguard.mdm.server.operations;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class OperationResponse {

    @NotNull private String message;
    @NotNull @Enumerated(EnumType.STRING) private OperationStatus status;

    public OperationResponse() {
        
        this.setStatus(OperationStatus.FAILURE);
        this.setMessage("Initialization Exception");
    }

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