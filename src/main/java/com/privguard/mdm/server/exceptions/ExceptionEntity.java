package com.privguard.mdm.server.exceptions;

import java.time.LocalDateTime;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="exceptions")
public class ExceptionEntity extends BaseEntity {
    

    @NotNull private String summury;
    @NotNull private String stacktrace;
    @NotNull private LocalDateTime timestamp;
    @NotNull @Enumerated(EnumType.STRING) private ExceptionTypes type;

    public String getSummury() {
        return summury;
    }

    public void setSummury(String summury) {
        this.summury = summury;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ExceptionTypes getType() {
        return type;
    }

    public void setType(ExceptionTypes type) {
        this.type = type;
    }
}
