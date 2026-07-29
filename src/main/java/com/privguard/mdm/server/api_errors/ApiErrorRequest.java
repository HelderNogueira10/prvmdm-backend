package com.privguard.mdm.server.api_errors;

import jakarta.validation.constraints.NotNull;

public class ApiErrorRequest {
    
    @NotNull private String error;
    @NotNull private String messages;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
    
}
