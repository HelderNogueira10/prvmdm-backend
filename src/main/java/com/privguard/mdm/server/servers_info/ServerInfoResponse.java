package com.privguard.mdm.server.servers_info;

import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.server_health.ServerHealthStatus;
import jakarta.validation.constraints.NotNull;

public class ServerInfoResponse {

    @NotNull private Long serverId;
    @NotNull private String serverIP;
    @NotNull private String serverName;
    @NotNull private Integer serverPort;
    @NotNull private ServerHealthStatus serverStatus;

    public Long getServerId() { return serverId; }
    public String getServerName() { return serverName; }
    public String getServerIP() { return serverIP; }
    public Integer getServerPort() { return serverPort; }
    public ServerHealthStatus getServerStatus() { return serverStatus; }

    public void setServerId(Long _serverId) { this.serverId = _serverId; }
    public void setServerName(String _serverName) { this.serverName = _serverName; }
    public void setServerIP(String _serverIP) { this.serverIP = _serverIP; }
    public void setServerPort(Integer _serverPort) { this.serverPort = _serverPort; }
    public void setServerStatus(ServerHealthStatus _serverStatus) { this.serverStatus = _serverStatus; }
}
