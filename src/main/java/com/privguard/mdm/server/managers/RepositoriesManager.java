package com.privguard.mdm.server.managers;

import org.springframework.stereotype.Component;

import com.privguard.mdm.server.account.AccountsRepository;
import com.privguard.mdm.server.enrollment.EnrollmentRepository;
import com.privguard.mdm.server.user_accounts.UserAccountRepository;

@Component
public class RepositoriesManager {

    private static RepositoriesManager INSTANCE;

    private final AccountsRepository accountsRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserAccountRepository userAccountsRepository;

    public RepositoriesManager(
        AccountsRepository _accountsRepository,
        UserAccountRepository _userAccountsRepository,
        EnrollmentRepository _enrollmentRepository) {

        if(INSTANCE == null)
            INSTANCE = this;

        this.accountsRepository = _accountsRepository;
        this.enrollmentRepository = _enrollmentRepository;
        this.userAccountsRepository = _userAccountsRepository;
    }

    
    public static RepositoriesManager getInstance() { return INSTANCE; }

    public AccountsRepository getAccountsRepository() {
        return accountsRepository;
    }

    public UserAccountRepository getUserAccountsRepository() {
        return userAccountsRepository;
    }
}