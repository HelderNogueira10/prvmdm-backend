package com.privguard.mdm.server.account.account_tokens;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.tokens.TokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountTokensRepository extends JpaRepository<AccountTokenEntity, Long> {

    Optional<AccountTokenEntity> findByJti(String _jti);
    List<AccountTokenEntity> findByAccount(AccountEntity _account);
    List<AccountTokenEntity> findByAccountAndStatus(AccountEntity _account, TokenStatus _status);
}
