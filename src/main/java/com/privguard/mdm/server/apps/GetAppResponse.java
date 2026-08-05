package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.app_versions.AppVersionResponse;
import com.privguard.mdm.server.operations.OperationResponse;

import java.time.LocalDateTime;
import java.util.List;

public class GetAppResponse extends OperationResponse {

    private Integer id;
    private String name;
    private String description;
    private String packageName;
    private LocalDateTime createdAt;

    private Long totalAppSize;
    private Integer appFilesCount;
    private Integer appVersionsCount;
    private Integer installsCount;
    private Integer uninstallsCount;
    private AppVersionResponse version;

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

    public Integer getAppFilesCount() {
        return appFilesCount;
    }

    public void setAppFilesCount(Integer appFilesCount) {
        this.appFilesCount = appFilesCount;
    }

    public AppVersionResponse getVersion() {
        return version;
    }

    public void setVersion(AppVersionResponse version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAppVersionsCount() {
        return appVersionsCount;
    }

    public void setAppVersionsCount(Integer appVersionsCount) {
        this.appVersionsCount = appVersionsCount;
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

    public Long getTotalAppSize() {
        return totalAppSize;
    }

    public void setTotalAppSize(Long totalAppSize) {
        this.totalAppSize = totalAppSize;
    }
}
