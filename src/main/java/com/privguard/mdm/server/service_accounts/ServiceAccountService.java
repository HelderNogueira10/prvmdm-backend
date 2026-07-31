package com.privguard.mdm.server.service_accounts;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.account.AccountStatus;
import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.account.AccountsService;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.utils.StringsGenerator;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccountService {

    private final ServiceAccountRepository serviceAccountRepository;

    public ServiceAccountService(ServiceAccountRepository serviceAccountRepository) {
        this.serviceAccountRepository = serviceAccountRepository;
    }

    public ServiceAccountResponse add(ServiceAccountRequest _req, AccountEntity _account) {

        ServiceAccountResponse response = new ServiceAccountResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            String accountSecret = StringsGenerator.generateRandomString(70);
            ServiceAccountEntity serviceAccount = serviceAccountRepository.findByName(_req.getName()).orElse(new ServiceAccountEntity());

            if(serviceAccount.getAccount() != null)
                throw new RuntimeException("service account already exists!");

            serviceAccount.setName(_req.getName());
            serviceAccount.setStatus(ServiceAccountStatus.ENABLED);
            serviceAccount.setUsername(_req.getUsername());
            serviceAccount.setSecret(accountSecret);
            serviceAccount.setAccount(_account);
            serviceAccountRepository.save(serviceAccount);

            response.setName(_req.getName());
            response.setSecret(accountSecret);
            response.setUsername(_req.getUsername());
            response.setAccountUuid(_account.getUuid());
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Service Account Add Successfully");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Adding Service Account Exception: " + _e.getMessage());
        }

        return response;
    }
}
