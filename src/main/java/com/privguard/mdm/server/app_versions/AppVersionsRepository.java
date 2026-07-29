package com.privguard.mdm.server.app_versions;

import com.privguard.mdm.server.apps.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppVersionsRepository extends JpaRepository<AppVersionEntity, Long> {

    Optional<AppVersionEntity> findByVersionCodeAndApp(String _versionCode, AppEntity _app);

    AppVersionEntity app(AppEntity app);
}
