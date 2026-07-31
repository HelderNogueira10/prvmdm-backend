package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.exceptions.InvalidCredentialsException;
import com.privguard.mdm.server.service_accounts.ServiceAccountEntity;
import com.privguard.mdm.server.service_accounts.ServiceAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ServiceAuthenticationProvider implements IServiceAuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final ServiceAccountRepository serviceAccountRepository;

    public ServiceAuthenticationProvider(PasswordEncoder _passwordEncoder,
                                         ServiceAccountRepository _serviceAccountRepository) {

        this.passwordEncoder = _passwordEncoder;
        this.serviceAccountRepository = _serviceAccountRepository;
    }

    @Override
    public AccountEntity authenticate(ServiceAuthenticationRequest _request) {

        ServiceAccountEntity serviceAccount = serviceAccountRepository.findByUsername(_request.getUsername())
                .orElseThrow(() -> new RuntimeException("service account has not been found..."));

        if(!passwordEncoder.matches(_request.getSecret(), serviceAccount.getSecret()))
            throw new InvalidCredentialsException();

        return serviceAccount.getAccount();
    }

}
