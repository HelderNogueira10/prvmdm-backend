package com.privguard.mdm.server.command;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class FetchCommandsResponse {
    
    @NotNull private String deviceUuid;
    private String message;
    private List<CommandResponse> commands;

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CommandResponse> getCommands() {
        return commands;
    }

    public void setCommands(List<CommandResponse> commands) {
        this.commands = commands;
    }
}
