package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class GetPaginatedAppsResponse extends OperationResponse {

    private int pageId;
    private Long totalCount;
    private List<FetchBasicApplicationResponse> apps;

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<FetchBasicApplicationResponse> getApps() {
        return apps;
    }

    public void setApps(List<FetchBasicApplicationResponse> apps) {
        this.apps = apps;
    }
}
