package com.privguard.mdm.server.apps;

import jakarta.validation.constraints.NotNull;

public class AddAppRequest {

    @NotNull private String name;
    @NotNull private String description;
    @NotNull private String packageName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
