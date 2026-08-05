package com.privguard.mdm.server.app_files;

import com.privguard.mdm.server.app_versions.AppVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppFilesRepository extends JpaRepository<AppFileEntity, Long> {

    List<AppFileEntity> findAllByAppVersion(AppVersionEntity _appVersion);
    void deleteByAppVersion(AppVersionEntity _appVersion);
}
