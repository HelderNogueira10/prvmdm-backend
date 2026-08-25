package com.privguard.mdm.server.apps;

import jakarta.validation.constraints.NotNull;

public class EditAppRequest {

    @NotNull private Long id;
    @NotNull private String name;
    @NotNull private String iconUrl;
    @NotNull private String appDescription;
    @NotNull private String packageName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }
}
