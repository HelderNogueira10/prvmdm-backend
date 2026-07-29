package com.privguard.mdm.server.app_files;

import jakarta.validation.constraints.NotNull;

public class AddAppFileRequest {

    @NotNull private Long appVersionId;
    @NotNull private String filename;

    public Long getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(Long appVersionId) {
        this.appVersionId = appVersionId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
