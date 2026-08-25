package com.privguard.mdm.server.account.account_tokens;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.auth.AuthenticationResponse;
import com.privguard.mdm.server.auth.AuthenticationStatus;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.security.JwtService;
import com.privguard.mdm.server.tokens.TokenStatus;
import com.privguard.mdm.server.tokens.TokensRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountTokenService {

    private final JwtService jwtService;
    private final AccountTokensRepository accountTokensRepository;
    private final TokensRepository tokensRepository;

    public AccountTokenService(JwtService _jwtService, AccountTokensRepository _accountTokensRepo, TokensRepository tokensRepository) {

        this.jwtService = _jwtService;
        this.accountTokensRepository = _accountTokensRepo;
        this.tokensRepository = tokensRepository;
    }

    public AuthenticatedAccount validateToken(String _jti) {

        AccountTokenEntity token = accountTokensRepository.findByJti(_jti)
                .orElseThrow(() -> new RuntimeException("token is invalid"));

        if(token.getStatus() != TokenStatus.ACTIVE)
            throw new RuntimeException("token is invalid");

        if (token.getExpireAt().isBefore(LocalDateTime.now())) {

            token.setStatus(TokenStatus.EXPIRED);
            accountTokensRepository.save(token);
            throw new RuntimeException("token has expired");
        }

        AuthenticatedAccount account = new AuthenticatedAccount();
        account.setId(token.getAccount().getId());
        account.setUuid(token.getAccount().getUuid());
        account.setType(token.getAccount().getType());

        return account;
    }

    @Transactional
    public void revokeTokens(AccountEntity _account) {

        List<AccountTokenEntity> activeTokens = accountTokensRepository.findByAccountAndStatus(_account, TokenStatus.ACTIVE);
        for(AccountTokenEntity accountToken : activeTokens)
            accountToken.setStatus(TokenStatus.REVOKED);

        accountTokensRepository.saveAll(activeTokens);
    }

    @Transactional
    public AuthenticationResponse issueToken(AccountEntity _account) {

        String jti = UUID.randomUUID().toString();
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(24, ChronoUnit.HOURS);

        TokenGenerationRequest request = new TokenGenerationRequest();

        request.setAccountUuid(_account.getUuid());
        request.setJti(jti);
        request.setIssuedAt(issuedAt);
        request.setExpireAt(expiresAt);
        request.setAccountType(_account.getType());
        String jwt = jwtService.generateToken(request);

        AccountTokenEntity accountToken = new AccountTokenEntity();
        accountToken.setJti(jti);
        accountToken.setAccount(_account);
        accountToken.setStatus(TokenStatus.ACTIVE);
        accountToken.setExpireAt(LocalDateTime.ofInstant(expiresAt, ZoneId.systemDefault()));
        accountTokensRepository.save(accountToken);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwt);
        response.setAuthenticationStatus(AuthenticationStatus.GRANTED);
        response.setExpiresAt(LocalDateTime.ofInstant(expiresAt, ZoneId.systemDefault()).toString());
        response.setAccountUuid(_account.getUuid());
        response.setStatus(OperationStatus.SUCCESS);
        response.setMessage("Device Account Credentials Have Been Validated!");
        return response;
    }
}
