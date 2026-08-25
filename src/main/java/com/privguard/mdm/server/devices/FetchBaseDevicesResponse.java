package com.privguard.mdm.server.devices;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class FetchBaseDevicesResponse extends OperationResponse {

    private Long totalCount;
    private List<FetchBaseDeviceResponse> devices;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<FetchBaseDeviceResponse> getDevices() {
        return devices;
    }

    public void setDevices(List<FetchBaseDeviceResponse> devices) {
        this.devices = devices;
    }
}
