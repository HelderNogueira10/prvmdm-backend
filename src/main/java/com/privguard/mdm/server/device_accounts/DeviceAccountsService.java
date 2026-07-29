package com.privguard.mdm.server.device_accounts;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.privguard.mdm.server.account.AccountsRepository;
import com.privguard.mdm.server.utils.StringsGenerator;

@Service
public class DeviceAccountsService {
    
    private final PasswordEncoder passwordEncoder;
    private AccountsRepository accountsRepository;
    private DeviceAccountsRepository mRepository;

    public DeviceAccountsService(DeviceAccountsRepository _repo, AccountsRepository _accountRepository, PasswordEncoder passwordEncoder) {

        this.mRepository = _repo;
        this.passwordEncoder = passwordEncoder;
    }

    public DeviceAccountEntity add(DeviceAccountRequest _request) {

        DeviceAccountEntity deviceAccount = new DeviceAccountEntity();
        deviceAccount.setAccount(_request.getAccount());
        deviceAccount.setImei(_request.getImei());
        deviceAccount.setLastSeen(LocalDateTime.now());
        deviceAccount.setSecret(passwordEncoder.encode(_request.getSecret()));
        mRepository.save(deviceAccount);

        return deviceAccount;
    }
}
