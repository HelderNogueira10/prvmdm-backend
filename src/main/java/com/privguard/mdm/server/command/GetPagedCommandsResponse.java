package com.privguard.mdm.server.command;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class GetPagedCommandsResponse extends OperationResponse {

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<GetCommandResponse> getCommands() {
        return commands;
    }

    public void setCommands(List<GetCommandResponse> commands) {
        this.commands = commands;
    }

    private int pageSize;
    private List<GetCommandResponse> commands;
}
