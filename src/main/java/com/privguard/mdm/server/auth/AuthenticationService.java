package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.account.account_tokens.AccountTokensRepository;
import com.privguard.mdm.server.security.JwtService;
import com.privguard.mdm.server.account.account_tokens.AccountTokenService;
import com.privguard.mdm.server.service_accounts.ServiceAccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthenticationService {

    private final JwtService jwtService;
    private final AccountTokenService tokensService;
    private final AccountTokensRepository accountTokensRepository;

    private final ApiAuthenticationProvider apiAuthenticationProvider;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final DeviceAuthenticationProvider deviceAuthenticationProvider;
    private final ServiceAuthenticationProvider serviceAuthenticationProvider;

    private final ServiceAccountService serviceAccountService;

    public AuthenticationService(
            JwtService _jwtService,
            AccountTokenService _tokensService,
            AccountTokensRepository _accountTokensRepo,
            ApiAuthenticationProvider _apiAuthenticationProvider,
            UserAuthenticationProvider _userAuthProvider,
            DeviceAuthenticationProvider _deviceAuthProvider, ServiceAccountService serviceAccountService,
            ServiceAuthenticationProvider _serviceAuthenticationProvider) {

        this.jwtService = _jwtService;
        this.tokensService = _tokensService;
        this.accountTokensRepository = _accountTokensRepo;
        this.apiAuthenticationProvider = _apiAuthenticationProvider;
        this.userAuthenticationProvider = _userAuthProvider;
        this.deviceAuthenticationProvider = _deviceAuthProvider;
        this.serviceAccountService = serviceAccountService;
        this.serviceAuthenticationProvider = _serviceAuthenticationProvider;
    }

    public AuthenticationResponse authenticate(UserAuthenticationRequest _userAuthRequest) {

        AccountEntity account = userAuthenticationProvider.authenticate(_userAuthRequest);
        return tokensService.issueToken(account);
    }

    public AuthenticationResponse authenticate(DeviceAuthenticationRequest _deviceAuthRequest) {

        AccountEntity account = deviceAuthenticationProvider.authenticate(_deviceAuthRequest);
        tokensService.revokeTokens(account);
        return tokensService.issueToken(account);
    }

    public AuthenticationResponse authenticate(ServiceAuthenticationRequest _request) {

        AccountEntity account = serviceAuthenticationProvider.authenticate(_request);
        return tokensService.issueToken(account);
    }

    public AuthenticationResponse authenticate(ApiAuthenticationRequest _request) {

        AccountEntity account = apiAuthenticationProvider.authenticate(_request);
        return tokensService.issueToken(account);
    }
}
