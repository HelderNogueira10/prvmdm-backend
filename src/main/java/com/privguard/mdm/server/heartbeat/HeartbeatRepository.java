package com.privguard.mdm.server.heartbeat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartbeatRepository extends JpaRepository<HeartbeatEntity, Long>{
    
}
