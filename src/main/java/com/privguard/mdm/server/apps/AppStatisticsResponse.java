package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.operations.OperationResponse;

public class AppStatisticsResponse extends OperationResponse {

    private Integer totalApps;
    private Integer pendingInstall;
    private Integer failedInstall;
    private Integer totalAppFiles;
    private Integer totalAppVersions;
    private Integer totalAppInstalls;
    private Integer totalAppUninstalls;

    public Integer getTotalApps() { return totalApps; }
    public Integer getPendingInstall() { return pendingInstall; }
    public Integer getFailedInstall() { return failedInstall; }
    public Integer getTotalAppFiles() { return totalAppFiles; }
    public Integer getTotalAppVersions() { return totalAppVersions; }
    public Integer getTotalAppInstalls() { return totalAppInstalls; }

    public void setTotalApps(Integer totalApps) {
        this.totalApps = totalApps;
    }

    public void setPendingInstall(Integer pendingInstall) {
        this.pendingInstall = pendingInstall;
    }

    public void setFailedInstall(Integer failedInstall) {
        this.failedInstall = failedInstall;
    }

    public void setTotalAppFiles(Integer totalAppFiles) {
        this.totalAppFiles = totalAppFiles;
    }

    public void setTotalAppVersions(Integer totalAppVersions) {
        this.totalAppVersions = totalAppVersions;
    }

    public void setTotalAppInstalls(Integer totalAppInstalls) {
        this.totalAppInstalls = totalAppInstalls;
    }

    public Integer getTotalAppUninstalls() {
        return totalAppUninstalls;
    }

    public void setTotalAppUninstalls(Integer totalAppUninstalls) {
        this.totalAppUninstalls = totalAppUninstalls;
    }
}
