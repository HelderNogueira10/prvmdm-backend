package com.privguard.mdm.server.logs;

import java.time.LocalDateTime;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="logs")
public class LogEntity extends BaseEntity {
    

    @NotNull private String summury;
    @NotNull private String description;
    @NotNull private LocalDateTime timestamp;

    public String getSummury() {
        return summury;
    }

    public void setSummury(String summury) {
        this.summury = summury;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
