package com.privguard.mdm.server.device_inventory;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceInventoryRepository extends JpaRepository<DeviceInventoryEntity, Long> {

    Optional<DeviceInventoryEntity> findByDevice(DeviceAccountEntity _device);
}
