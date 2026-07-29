package com.privguard.mdm.server.device_accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.privguard.mdm.server.account.AccountEntity;

import java.util.Optional;


public interface DeviceAccountsRepository extends JpaRepository<DeviceAccountEntity, Long> {

    <Optional> DeviceAccountEntity findByAccount(AccountEntity _account);
    Optional<DeviceAccountEntity> findByAccount_Uuid(String uuid);

}
