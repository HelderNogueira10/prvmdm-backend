package com.privguard.mdm.server.account;

import com.privguard.mdm.server.device_accounts.DeviceAccountRequest;
import com.privguard.mdm.server.device_accounts.DeviceAccountResponse;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.service_accounts.ServiceAccountRequest;
import com.privguard.mdm.server.service_accounts.ServiceAccountResponse;
import com.privguard.mdm.server.service_accounts.ServiceAccountService;
import com.privguard.mdm.server.user_accounts.UserAccountRequest;
import com.privguard.mdm.server.user_accounts.UserAccountResponse;
import com.privguard.mdm.server.user_accounts.UserAccountsService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    private final AccountsService accountsService;
    private final UserAccountsService userAccountsService;

    public AccountsController(AccountsService accountsService, UserAccountsService userAccountsService) {
        this.accountsService = accountsService;
        this.userAccountsService = userAccountsService;
    }

    @PostMapping("/add/serviceAccount")
    public ServiceAccountResponse addServiceAccount(@Valid @RequestBody ServiceAccountRequest _req, Authentication _auth) {

        return accountsService.addServiceAccount(_req, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @PostMapping("/add/userAccount")
    public UserAccountResponse addUserAccount(@Valid @RequestBody UserAccountRequest _req, AuthenticatedAccount _account) {

        return accountsService.addUserAccount(_req, _account);
    }
}
