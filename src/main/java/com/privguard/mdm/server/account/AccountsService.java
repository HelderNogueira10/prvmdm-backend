package com.privguard.mdm.server.account;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AccountsService {
    
    private AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository _repo) {

        this.accountsRepository = _repo;
    }

    public AccountEntity addAgent() {

        AccountEntity account = new AccountEntity();
        account.setType(AccountTypes.ANDROID_ACCOUNT);
        account.setStatus(AccountStatus.PENDING);
        account.setUuid(UUID.randomUUID().toString());
        accountsRepository.save(account);

        return account;
    }

    public AccountEntity addUser(String _username, String _password) {

        AccountEntity account = new AccountEntity();
        account.setType(AccountTypes.ANDROID_ACCOUNT);
        account.setStatus(AccountStatus.ENABLED);
        account.setUuid(UUID.randomUUID().toString());
        accountsRepository.save(account);

        return account;
    }

    public AccountEntity getAccount(String _uuid) {

        return accountsRepository.findByUuid(_uuid).orElse(null);
    }
}
