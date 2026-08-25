package com.privguard.mdm.server.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    
    private final AuthenticationService mService;

    public AuthenticationController(AuthenticationService _authenticationService) {

        this.mService = _authenticationService;
    }
    
    @PostMapping("/user_account")
    public AuthenticationResponse authUser(@Valid @RequestBody UserAuthenticationRequest _request) {

        return mService.authenticate(_request);
    }

    @PostMapping("/device_account")
    public AuthenticationResponse authAgent(@Valid @RequestBody DeviceAuthenticationRequest _request) {

        return mService.authenticate(_request);
    }

    @PostMapping("/service_account")
    public AuthenticationResponse authServiceAcount(@Valid @RequestBody ServiceAuthenticationRequest _request) {

        return mService.authenticate(_request);
    }

    @PostMapping("/api_account")
    public AuthenticationResponse authApiAccount(@Valid @RequestBody ApiAuthenticationRequest _request) {

        return mService.authenticate(_request);
    }
}
