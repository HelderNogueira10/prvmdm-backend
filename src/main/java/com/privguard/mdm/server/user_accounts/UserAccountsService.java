package com.privguard.mdm.server.user_accounts;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.account.AccountStatus;
import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.exceptions.ExceptionsManager;
import com.privguard.mdm.server.managers.RepositoriesManager;

@Service
public class UserAccountsService {
    
    private final PasswordEncoder passwordEncoder;
    private final ExceptionsManager exceptionsManager;

    public UserAccountsService(PasswordEncoder _passwordEncoder, ExceptionsManager _exceptionsManager) {

        this.passwordEncoder = _passwordEncoder;
        this.exceptionsManager = _exceptionsManager;
    }

    public UserAccountResponse createUserAccount(UserAccountRequest _request) {

        UserAccountResponse response = new UserAccountResponse();
        response.setMessage("Account Creation Failed!");

        try {

            //CREATE ACCOUNT
            String accountUuid = UUID.randomUUID().toString();

            AccountEntity account = new AccountEntity();
            account.setType(AccountTypes.USER_ACCOUNT);
            account.setUuid(accountUuid);
            account.setStatus(AccountStatus.ENABLED);
            RepositoriesManager.getInstance().getAccountsRepository().save(account);

            //CREATE USER ACCOUNT
            UserAccountEntity userAccount = new UserAccountEntity();
            userAccount.setAccountId(account);
            userAccount.setName(_request.getName());
            userAccount.setEmail(_request.getEmail());
            userAccount.setUsername(_request.getUsername());
            userAccount.setPassword(passwordEncoder.encode(_request.getPassword()));
            userAccount.setRole(UserAccountRoles.SUPER_ADMIN);
            RepositoriesManager.getInstance().getUserAccountsRepository().save(userAccount);

            response.setMessage("OK");
            response.setUserUuid(accountUuid);
        }
        catch(Exception _e) { throw new RuntimeException(exceptionsManager.onErrorException("User Account Creation Failed: ", _e.getMessage())); }
        
        return response; 
    }

}
