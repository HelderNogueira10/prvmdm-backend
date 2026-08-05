package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.operations.OperationResponse;

public class GetAppDetailsResponse extends OperationResponse {

    private Long size;
    private Integer id;
    private String name;
    private String packageName;

    private Integer errorsCount;
    private Integer versionsCount;
    private Integer installsCount;
    private Integer uninstallsCount;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getErrorsCount() {
        return errorsCount;
    }

    public void setErrorsCount(Integer errorsCount) {
        this.errorsCount = errorsCount;
    }

    public Integer getVersionsCount() {
        return versionsCount;
    }

    public void setVersionsCount(Integer versionsCount) {
        this.versionsCount = versionsCount;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
