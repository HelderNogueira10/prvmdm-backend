package com.privguard.mdm.server.api_errors;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiErrorsRepository extends JpaRepository<ApiErrorEntity, Long>{
    
}
