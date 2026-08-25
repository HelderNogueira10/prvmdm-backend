package com.privguard.mdm.server.command;

import java.util.List;

import com.privguard.mdm.server.apps.AppEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;

public interface CommandsRepository extends JpaRepository<CommandEntity, Long> {

    Long countByStatus(CommandStatus _status);

    List<CommandEntity> findAllByStatus(CommandStatus _status);
    List<CommandEntity> findByTypeAndStatus(CommandType _type, CommandStatus _status);
    List<CommandEntity> findAllByTypeAndStatus(CommandType _type, CommandStatus _status);
    List<CommandEntity> findAllByDeviceIdAndStatus(DeviceAccountEntity _deviceId, CommandStatus _status);
    <Optional> List<CommandEntity> findByDeviceIdAndStatus(DeviceAccountEntity _device, CommandStatus _status);
    List<CommandEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
