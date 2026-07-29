package com.privguard.mdm.server.api_errors;

import java.time.LocalDateTime;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="api_errors")
public class ApiErrorEntity extends BaseEntity {
    
    @NotNull private String error;
    @NotNull private String messages;
    @NotNull private LocalDateTime timestamp;
    @NotNull private Integer responseStatus;

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }
}
