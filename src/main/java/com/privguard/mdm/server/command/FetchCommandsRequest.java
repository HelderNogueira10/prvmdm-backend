package com.privguard.mdm.server.command;

import jakarta.validation.constraints.NotNull;

public class FetchCommandsRequest {
    
    @NotNull private String deviceUuid;
    @NotNull private String authenticationToken;

    public void setDeviceUuid(String _deviceUuid) {

        this.deviceUuid = _deviceUuid;
    }

    public String getDeviceUuid() { return deviceUuid; }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
