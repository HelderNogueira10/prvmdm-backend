package com.privguard.mdm.server.exceptions;

import jakarta.validation.constraints.NotNull;

public class ExceptionResponse {
    
    @NotNull private String exception;

    public ExceptionResponse(String _exception) {

        this.exception = _exception;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
