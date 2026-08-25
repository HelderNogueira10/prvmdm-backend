package com.privguard.mdm.server.global;

import com.privguard.mdm.server.account.AccountsRepository;
import com.privguard.mdm.server.account.account_tokens.AccountTokensRepository;
import com.privguard.mdm.server.api_errors.ApiErrorsRepository;
import com.privguard.mdm.server.command.CommandsRepository;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.enrollment.EnrollmentRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoriesHub {

    private static RepositoriesHub INSTANCE;
    private final AccountsRepository accountsRepo;
    private final AccountTokensRepository accountTokensRepo;
    private final ApiErrorsRepository apiErrorsRepo;
    private final CommandsRepository commandsRepo;
    private final DeviceAccountsRepository deviceAccountsRepo;
    private final EnrollmentRepository enrollmentRepo;

    public RepositoriesHub(
            AccountsRepository _accountsRepo,
            AccountTokensRepository _accountTokensRepo,
            ApiErrorsRepository _apiErrorsRepo,
            CommandsRepository _commandsRepo,
            DeviceAccountsRepository _deviceAccountsRepo,
            EnrollmentRepository _enrollmentReppo
    ) {

        if(INSTANCE == null)
            INSTANCE = this;

        this.accountsRepo = _accountsRepo;
        this.commandsRepo = _commandsRepo;
        this.apiErrorsRepo = _apiErrorsRepo;
        this.enrollmentRepo = _enrollmentReppo;
        this.accountTokensRepo = _accountTokensRepo;
        this.deviceAccountsRepo = _deviceAccountsRepo;
    }

    public AccountsRepository getAccounts() { return accountsRepo; }
    public ApiErrorsRepository getApiErrors() { return apiErrorsRepo; }
    public EnrollmentRepository getEnrollments() { return enrollmentRepo; }
    public AccountTokensRepository getAccountTokens() { return accountTokensRepo; }
    public DeviceAccountsRepository getDeviceAccounts() { return deviceAccountsRepo; }

    public static RepositoriesHub getInstance() { return INSTANCE; }
}
