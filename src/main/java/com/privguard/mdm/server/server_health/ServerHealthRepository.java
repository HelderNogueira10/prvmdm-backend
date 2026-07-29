package com.privguard.mdm.server.server_health;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerHealthRepository extends JpaRepository<ServerHealthEntity, Long>{
    
}
