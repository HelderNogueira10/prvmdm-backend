package com.privguard.mdm.server.command;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;

public interface CommandsRepository extends JpaRepository<CommandEntity, Long> {
    
    <Optional> List<CommandEntity> findByDeviceIdAndStatus(DeviceAccountEntity _device, CommandStatus _status);
}
