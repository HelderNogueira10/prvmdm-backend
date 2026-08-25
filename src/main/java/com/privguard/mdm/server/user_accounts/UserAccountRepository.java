package com.privguard.mdm.server.user_accounts;

import com.privguard.mdm.server.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long>{
    
    Optional<UserAccountEntity> findByUsername(String _username);
    Optional<UserAccountRepository> findByAccountId(AccountEntity _account);
    boolean existsByUsername(String _username);
    boolean existsByEmail(String _email);
}
