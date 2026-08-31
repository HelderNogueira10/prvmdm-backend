package com.privguard.mdm.server.command_types;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class GetCommandTypesResponse extends OperationResponse {

    private List<GetCommandTypeResponse> commandTypes;

    public List<GetCommandTypeResponse> getCommandTypes() {
        return commandTypes;
    }

    public void setCommandTypes(List<GetCommandTypeResponse> commandTypes) {
        this.commandTypes = commandTypes;
    }
}
