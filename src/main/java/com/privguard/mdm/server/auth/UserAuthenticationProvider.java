package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.exceptions.AuthenticationFailure;
import com.privguard.mdm.server.exceptions.InvalidCredentialsException;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.user_accounts.UserAccountEntity;
import com.privguard.mdm.server.user_accounts.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements IUserAuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountsRepo;

    public UserAuthenticationProvider(
            PasswordEncoder _passwordEncoder,
            UserAccountRepository _userAccountsRepo) {

        this.passwordEncoder = _passwordEncoder;
        this.userAccountsRepo = _userAccountsRepo;
    }

    @Override
    public AccountEntity authenticate(UserAuthenticationRequest _request) {

        try {

            UserAccountEntity userAccount = userAccountsRepo.findByUsername(_request.getUsername())
                    .orElseThrow(AuthenticationFailure::new);

            if(!passwordEncoder.matches(_request.getPassword(), userAccount.getPassword()))
                throw new AuthenticationFailure();

            return userAccount.getAccountId();
        }
        catch(Exception _e) { throw new RuntimeException(_e.getMessage()); }
    }

    private OperationResponse onFailure(String _err) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);
        response.setMessage("Authentication Exception: " + _err);

        return response;
    }
}
