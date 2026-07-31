package com.privguard.mdm.server.account;

import java.util.UUID;

import com.privguard.mdm.server.device_accounts.DeviceAccountRequest;
import com.privguard.mdm.server.device_accounts.DeviceAccountResponse;
import com.privguard.mdm.server.device_accounts.DeviceAccountsService;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.service_accounts.ServiceAccountRequest;
import com.privguard.mdm.server.service_accounts.ServiceAccountResponse;
import com.privguard.mdm.server.service_accounts.ServiceAccountService;
import com.privguard.mdm.server.user_accounts.UserAccountRequest;
import com.privguard.mdm.server.user_accounts.UserAccountResponse;
import com.privguard.mdm.server.user_accounts.UserAccountsService;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

    private final ServiceAccountService serviceAccountService;
    private final UserAccountsService userAccountsService;
    private final DeviceAccountsService deviceAccountsService;
    private AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository _repo, ServiceAccountService serviceAccountService, UserAccountsService userAccountsService, DeviceAccountsService deviceAccountsService) {

        this.accountsRepository = _repo;
        this.serviceAccountService = serviceAccountService;
        this.userAccountsService = userAccountsService;
        this.deviceAccountsService = deviceAccountsService;
    }

    public DeviceAccountResponse addDeviceAccount(DeviceAccountRequest _req) {

        AccountEntity account = createBaseAccount(AccountTypes.ANDROID_ACCOUNT, AccountStatus.PENDING);
        return deviceAccountsService.add(_req, account);
    }

    public ServiceAccountResponse addServiceAccount(ServiceAccountRequest _req, AuthenticatedAccount _account) {

        System.out.println("Account Type: " + _account.getType());
        if(_account.getType() != AccountTypes.USER_ACCOUNT)
            return null;

        AccountEntity account = createBaseAccount(AccountTypes.SERVICE_ACCOUNT, AccountStatus.ENABLED);
        return serviceAccountService.add(_req, account);
    }

    public UserAccountResponse addUserAccount(UserAccountRequest _request, AuthenticatedAccount _account) {

        if(_account.getType() != AccountTypes.USER_ACCOUNT && _account.getType() != AccountTypes.SERVICE_ACCOUNT)
            return null;

        AccountEntity account = createBaseAccount(AccountTypes.USER_ACCOUNT, AccountStatus.ENABLED);
        return userAccountsService.add(_request, account);
    }

    private AccountEntity createBaseAccount(AccountTypes _accountType, AccountStatus _status) {

        AccountEntity account = new AccountEntity();
        account.setType(_accountType);
        account.setStatus(_status);
        account.setUuid(UUID.randomUUID().toString());
        accountsRepository.save(account);

        return account;
    }

    public AccountEntity getAccount(String _uuid) {

        return accountsRepository.findByUuid(_uuid).orElse(null);
    }
}
