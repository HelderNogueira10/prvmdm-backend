package com.privguard.mdm.server.command_types;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class GetCommandTypesResponse extends OperationResponse {

    private List<String> types;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
