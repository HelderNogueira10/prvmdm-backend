package com.privguard.mdm.server.apps;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AddAppFullRequest {

    @NotNull private String name;
    @NotNull private String description;
    @NotNull private String packageName;

    @NotNull private String versionCode;
    @NotNull private String versionName;
    @NotNull private List<String> filenames;

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

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

}
