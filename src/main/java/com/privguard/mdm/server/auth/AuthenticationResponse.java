package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.operations.OperationResponse;

import java.time.Instant;

public class AuthenticationResponse extends OperationResponse  {

    private String token;
    private String expiresAt;
    private String accountUuid;
    private AuthenticationStatus authenticationStatus;

    public String getToken() { return token; }
    public String getExpiresAt() { return expiresAt; }
    public String getAccountUuid() { return accountUuid; }
    public AuthenticationStatus getAuthenticationStatus() { return authenticationStatus; }


    public void setToken(String _token) { this.token = _token; }
    public void setExpiresAt(String _expiresAt) { this.expiresAt = _expiresAt; }
    public void setAccountUuid(String _accountUuid) { this.accountUuid = _accountUuid; }
    public void setAuthenticationStatus(AuthenticationStatus _authStatus) { this.authenticationStatus = _authStatus; }
}
