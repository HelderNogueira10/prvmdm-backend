package com.privguard.mdm.server.server_health;

import com.privguard.mdm.server.operations.OperationResponse;

import com.privguard.mdm.server.servers_info.ServerInfoResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class ServerHealthResponse extends OperationResponse  {

    private Integer preferedServerId;
    private String timestamp;
    private List<ServerInfoResponse> availableServers;

    public Integer getPreferedServerId() { return preferedServerId; }
    public String getTimestamp() { return timestamp; }
    public List<ServerInfoResponse> getAvailableServers() {
        return availableServers;
    }

    public void setPreferedServerId(Integer _preferedServerId) { this.preferedServerId = _preferedServerId; }
    public void setTimestamp(String _timestamp) { this.timestamp = _timestamp; }
    public void setAvailableServers(List<ServerInfoResponse> availableServers) {
        this.availableServers = availableServers;
    }
}

