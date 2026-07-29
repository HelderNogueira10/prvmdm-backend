package com.privguard.mdm.server.app_versions;

import jakarta.validation.constraints.NotNull;

public class AddAppVersionRequest {

    @NotNull private String versionCode;
    @NotNull private String versionName;
    @NotNull private Long appId;

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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
