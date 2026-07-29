package com.privguard.mdm.server.heartbeat;

import java.time.LocalDateTime;
import java.util.List;

import com.privguard.mdm.server.command.CommandResponse;
import com.privguard.mdm.server.operations.OperationResponse;

public class HeartbeatResponse extends OperationResponse {

    private String timestamp;
    private List<CommandResponse> pendingCommands;

    public void setTimestamp(String _timestamp) {

        this.timestamp = _timestamp;
    }
    public void setPendingCommands(List<CommandResponse> _commands) {

        this.pendingCommands = _commands;
    }

    public String getTimestamp() { return timestamp; }
    public List<CommandResponse> getPendingCommands() { return pendingCommands; }
}
