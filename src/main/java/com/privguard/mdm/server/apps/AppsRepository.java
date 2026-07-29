package com.privguard.mdm.server.apps;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppsRepository extends JpaRepository<AppEntity, Long> {

    Optional<AppEntity> findByPackageName(String _packageName);
}
