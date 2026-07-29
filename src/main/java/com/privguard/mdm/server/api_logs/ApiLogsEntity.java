package com.privguard.mdm.server.api_logs;

import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="api_logs")
public class ApiLogsEntity extends BaseEntity {
    
    @NotNull private String url;
    @NotNull private String source;
    @NotNull private String description;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
