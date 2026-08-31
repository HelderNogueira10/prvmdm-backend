package com.privguard.mdm.server.device_accounts;

import com.privguard.mdm.server.apps.AppEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.privguard.mdm.server.account.AccountEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface DeviceAccountsRepository extends JpaRepository<DeviceAccountEntity, Long> {

    Optional<DeviceAccountEntity> findByImei(String _imei);
    <Optional> DeviceAccountEntity findByAccount(AccountEntity _account);
    Optional<DeviceAccountEntity> findByAccount_Uuid(String uuid);
    Optional<DeviceAccountEntity> findByAccount_Id(Long id);

    List<DeviceAccountEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT d.hostname FROM DeviceAccountEntity d")
    List<String> findAllHostnames();

    boolean existsByHostname(String _hostname);
}
