package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.service_accounts.ServiceAccountRequest;
import com.privguard.mdm.server.service_accounts.ServiceAccountResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService _authenticationService) {

        this.authenticationService = _authenticationService;
    }
    
    @PostMapping("/user_account")
    public AuthenticationResponse authUser(@Valid @RequestBody UserAuthenticationRequest _request) {

        return authenticationService.authenticate(_request);
    }

    @PostMapping("/device_account")
    public AuthenticationResponse authAgent(@Valid @RequestBody DeviceAuthenticationRequest _request) {

        return authenticationService.authenticate(_request);
    }

    @PostMapping("/service_account")
    public AuthenticationResponse authServiceAcount(@Valid @RequestBody ServiceAuthenticationRequest _request) {

        return authenticationService.authenticate(_request);
    }

    @PostMapping("/api_account")
    public AuthenticationResponse authApiAccount(@Valid @RequestBody ApiAuthenticationRequest _request) {

        return authenticationService.authenticate(_request);
    }
}
