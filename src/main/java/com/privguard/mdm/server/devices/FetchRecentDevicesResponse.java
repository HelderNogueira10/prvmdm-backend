package com.privguard.mdm.server.devices;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class FetchRecentDevicesResponse extends OperationResponse {

    private int totalCount;
    private List<Long> deviceIds;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }
}
