package com.privguard.mdm.server.servers_info;

import com.privguard.mdm.server.server_health.ServerHealthStatus;
import jakarta.validation.constraints.NotNull;

public class ServerInfoRequest {

    @NotNull private String serverIP;
    @NotNull private String serverName;
    @NotNull private Integer serverPort;
    @NotNull private ServerHealthStatus serverStatus;

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public ServerHealthStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerHealthStatus serverStatus) {
        this.serverStatus = serverStatus;
    }
}
