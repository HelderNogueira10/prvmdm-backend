package com.privguard.mdm.server.app_versions;

import com.privguard.mdm.server.app_files.GetApplicationFileResponse;
import com.privguard.mdm.server.apps.AppFileResponse;
import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class AppVersionResponse extends OperationResponse {

    private Long id;
    private String appName;
    private String versionCode;
    private String versionName;
    private String createdAt;
    private String updatedAt;

    private List<GetApplicationFileResponse> files;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GetApplicationFileResponse> getFiles() {
        return files;
    }

    public void setFiles(List<GetApplicationFileResponse> files) {
        this.files = files;
    }
}
