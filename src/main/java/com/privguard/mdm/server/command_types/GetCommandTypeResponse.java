package com.privguard.mdm.server.command_types;

public class GetCommandTypeResponse {

    private Long commandId;
    private String commandType;

    public Long getCommandId() {
        return commandId;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }
}
