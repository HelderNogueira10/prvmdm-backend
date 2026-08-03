package com.privguard.mdm.server.api_accounts;

import com.beust.ah.A;
import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.account.AccountsService;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.utils.StringsGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApiAccountsService {

    private final AccountsService accountsService;
    private final ApiAccountsRepository apiAccountsRepository;

    public ApiAccountsService(AccountsService accountsService, ApiAccountsRepository apiAccountsRepository) {
        this.accountsService = accountsService;
        this.apiAccountsRepository = apiAccountsRepository;
    }

    public AddApiAccountResponse create(AddApiAccountRequest _request, AuthenticatedAccount _account) {

        AddApiAccountResponse response = new AddApiAccountResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {
            //check if account requesting the key is one of thoose permited types
            if(_account.getType() != AccountTypes.USER_ACCOUNT && _account.getType() != AccountTypes.SERVICE_ACCOUNT)
                throw new RuntimeException("insuficient permissions...");

            //check perms later

            //check if target account exists
            AccountEntity targetAccount = accountsService.getAccount(_request.getAccountUuid());
            if(targetAccount == null)
                throw new RuntimeException("target account not found...");

            String key = StringsGenerator.generateRandomString(120);
            ApiAccountEntity apiAccount = new ApiAccountEntity();
            apiAccount.setAccount(targetAccount);
            apiAccount.setAccountStatus(_request.getStatus());
            apiAccount.setKey(key);
            apiAccount.setExpireDate(LocalDateTime.now().plusHours(1));
            apiAccountsRepository.save(apiAccount);

            response.setKey(key);
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("api account added successfully.");
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("creating api key / account exception: " + _e.getMessage());
        }

        return response;
    }
}
