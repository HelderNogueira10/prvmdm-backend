package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.app_versions.GetApplicationVersionsResponse;
import com.privguard.mdm.server.operations.OperationResponse;

public class GetApplicationResponse extends OperationResponse {

    private Long id;
    private Integer installsCount;
    private Integer uninstallsCount;

    private String createdAt;
    private String updatedAt;

    private String name;
    private String iconUrl;
    private String packageName;
    private String appDescription;

    private GetApplicationVersionsResponse versions;

    public void setVersions(GetApplicationVersionsResponse versions) {
        this.versions = versions;
    }


    public Integer getInstallsCount() {
        return installsCount;
    }

    public void setInstallsCount(Integer installsCount) {
        this.installsCount = installsCount;
    }

    public Integer getUninstallsCount() {
        return uninstallsCount;
    }

    public void setUninstallsCount(Integer uninstallsCount) {
        this.uninstallsCount = uninstallsCount;
    }

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


    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GetApplicationVersionsResponse getVersions() {
        return versions;
    }

    public String getAppDescription() {
        return appDescription;
    }
}
