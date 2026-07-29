package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountsRepository;
import com.privguard.mdm.server.exceptions.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DeviceAuthenticationProvider implements IDeviceAuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final DeviceAccountsRepository deviceAccountsRepository;

    public DeviceAuthenticationProvider(PasswordEncoder _passwordEncoder,
                                        DeviceAccountsRepository _deviceAccountsRepository) {

        this.passwordEncoder = _passwordEncoder;
        this.deviceAccountsRepository = _deviceAccountsRepository;
    }

    @Override
    public AccountEntity authenticate(DeviceAuthenticationRequest _request) {

        DeviceAccountEntity deviceAccount = deviceAccountsRepository.findByAccount_Uuid(_request.getAccountUuid())
                .orElseThrow(InvalidCredentialsException::new);

        if(!passwordEncoder.matches(_request.getSecret(), deviceAccount.getSecret()))
            throw new InvalidCredentialsException();

        return deviceAccount.getAccount();
    }
}
