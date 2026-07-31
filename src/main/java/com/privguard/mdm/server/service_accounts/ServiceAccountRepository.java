package com.privguard.mdm.server.service_accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceAccountRepository extends JpaRepository<ServiceAccountEntity, Long> {

    Optional<ServiceAccountEntity> findByUsername(String _username);
    Optional<ServiceAccountEntity> findByName(String _name);
}
