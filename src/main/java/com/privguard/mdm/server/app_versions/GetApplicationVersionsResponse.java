package com.privguard.mdm.server.app_versions;

import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;

import java.util.List;

public class GetApplicationVersionsResponse extends OperationResponse {

    private Long appId;
    private List<AppVersionResponse> versions;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public List<AppVersionResponse> getVersions() {
        return versions;
    }

    public void setVersions(List<AppVersionResponse> versions) {
        this.versions = versions;
    }
}
