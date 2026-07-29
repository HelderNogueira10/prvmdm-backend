package com.privguard.mdm.server.servers_info;

import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.server_health.ServerHealthStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "servers_info")
public class ServerInfoEntity extends BaseEntity {

    @Column(nullable = false) private Long serverLoad;
    @Column(nullable = false) private String serverIP;
    @Column(nullable = false) private Integer serverPort;
    @Column(nullable = false, unique = true) private String serverName;
    @Column(nullable = false) @Enumerated(EnumType.STRING) private ServerHealthStatus serverStatus;

    public Long getServerLoad() { return serverLoad; }
    public String getServerIP() { return serverIP; }
    public String getServerName() { return serverName; }
    public Integer getServerPort() { return serverPort; }
    public ServerHealthStatus getServerStatus() { return serverStatus; }

    public void setServerLoad(Long _serverLoad) { this.serverLoad = _serverLoad; }
    public void setServerIP(String _serverIP) { this.serverIP = _serverIP; }
    public void setServerName(String _serverName) { this.serverName = _serverName; }
    public void setServerPort(Integer _serverPort) { this.serverPort = _serverPort; }
    public void setServerStatus(ServerHealthStatus _serverStatus) { this.serverStatus = _serverStatus; }
}
