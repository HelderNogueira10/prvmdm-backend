package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.operations.OperationResponse;

import java.time.LocalDateTime;
import java.util.List;

public class FetchAllBasicApplicationResponse extends OperationResponse  {

    private LocalDateTime timestamp;
    private List<FetchBasicApplicationResponse> apps;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<FetchBasicApplicationResponse> getApps() {
        return apps;
    }

    public void setApps(List<FetchBasicApplicationResponse> apps) {
        this.apps = apps;
    }
}
