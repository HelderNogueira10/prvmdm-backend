package com.privguard.mdm.server.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class AuthenticationController {
    
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService _authenticationService) {

        this.authenticationService = _authenticationService;
    }
    
    @PostMapping("/api/auth/user_account")
    public AuthenticationResponse authUser(@Valid @RequestBody UserAuthenticationRequest _request) {

        return authenticationService.authenticate(_request);
    }

    @PostMapping("/api/auth/device_account")
    public AuthenticationResponse authAgent(@Valid @RequestBody DeviceAuthenticationRequest _request) {

        return authenticationService.authenticate(_request);
    }
}
