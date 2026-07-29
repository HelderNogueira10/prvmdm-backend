package com.privguard.mdm.server.exceptions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepository extends JpaRepository<ExceptionEntity, Long>{
    
}
