package com.privguard.mdm.server.servers_info;

import com.privguard.mdm.server.server_health.ServerHealthStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServersInfoRepository extends JpaRepository<ServerInfoEntity, Long> {

    Optional<ServerInfoEntity> findByServerName(String _serverName);
    List<ServerInfoEntity> findAllByServerStatus(ServerHealthStatus _serverStatus);
}
