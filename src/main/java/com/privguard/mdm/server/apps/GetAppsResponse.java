package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.operations.OperationResponse;

import java.time.LocalDateTime;
import java.util.List;

public class GetAppsResponse extends OperationResponse {

    private Integer appsCount;
    private LocalDateTime timestamp;
    private List<GetApplicationResponse> appsResponse;

    public Integer getAppsCount() {
        return appsCount;
    }

    public void setAppsCount(Integer appsCount) {
        this.appsCount = appsCount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<GetApplicationResponse> getAppsResponse() {
        return appsResponse;
    }

    public void setAppsResponse(List<GetApplicationResponse> appsResponse) {
        this.appsResponse = appsResponse;
    }
}
