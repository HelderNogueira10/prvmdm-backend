package com.privguard.mdm.server.device_status;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceStatusRepository extends JpaRepository<DeviceStatusEntity, Long> {

    Optional<DeviceStatusEntity> findByDevice(DeviceAccountEntity _device);
}
