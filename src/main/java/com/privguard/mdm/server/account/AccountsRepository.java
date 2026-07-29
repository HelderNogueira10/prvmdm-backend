package com.privguard.mdm.server.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<AccountEntity, Long>{
    
    Optional<AccountEntity> findByUuid(String _uuid);
}
