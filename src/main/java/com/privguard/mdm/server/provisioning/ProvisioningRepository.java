package com.privguard.mdm.server.provisioning;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvisioningRepository extends JpaRepository<ProvisioningEntity, Long>{
    
    <Optional> ProvisioningEntity findBySchemaName(String _name);
}
