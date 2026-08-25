package com.privguard.mdm.server.apps;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppsRepository extends JpaRepository<AppEntity, Long> {

    Optional<AppEntity> findByPackageName(String _packageName);
    List<AppEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
