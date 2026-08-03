package com.privguard.mdm.server.api_accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiAccountsRepository extends JpaRepository<ApiAccountEntity, Long> {

    Optional<ApiAccountEntity> findByKey(String _key);
}
