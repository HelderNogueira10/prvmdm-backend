package com.privguard.mdm.server.operations;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationsRepository extends JpaRepository<OperationEntity, Long> {
    
}
