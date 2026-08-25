package com.privguard.mdm.server.inventory;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceInventoryRepository extends JpaRepository<InventoryEntity, Long> {

    Optional<InventoryEntity> findByDevice(DeviceAccountEntity _device);
}
