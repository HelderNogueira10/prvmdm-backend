package com.privguard.mdm.server.device_accounts;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class GetHostnamesResponse extends OperationResponse {

    private List<Long> ids;
    private List<String> hostnames;

    public List<String> getHostnames() {
        return hostnames;
    }

    public void setHostnames(List<String> hostnames) {
        this.hostnames = hostnames;
    }

    public List<Long> getIds() {
        return ids;
    }
    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
