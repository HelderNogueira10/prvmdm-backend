package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.api_accounts.ApiAccountEntity;
import com.privguard.mdm.server.api_accounts.ApiAccountStatus;
import com.privguard.mdm.server.api_accounts.ApiAccountsRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ApiAuthenticationProvider implements IAPIAuthenticationProvider {

    private ApiAccountsRepository apiAccountsRepository;

    public ApiAuthenticationProvider(ApiAccountsRepository _apiAccountsRepository) {

        this.apiAccountsRepository = _apiAccountsRepository;
    }

    @Override
    public AccountEntity authenticate(ApiAuthenticationRequest _request) {

        ApiAccountEntity apiAccount = apiAccountsRepository.findByKey(_request.getKey())
                .orElseThrow(() -> new RuntimeException("api account not found..."));

        if(apiAccount.getAccountStatus() != ApiAccountStatus.ENABLED)
            throw new RuntimeException("thia api account is not valid...");

        if(LocalDateTime.now().isAfter(apiAccount.getExpireDate()))
            throw new RuntimeException("api account key has expired.");

        return apiAccount.getAccount();
    }
}
