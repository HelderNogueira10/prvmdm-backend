package com.privguard.mdm.server.user_accounts;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.privguard.mdm.server.managers.ServicesManager;

import jakarta.validation.Valid;

@RestController
public class UserAccountsController {
    
    private UserAccountsService mService;

    public UserAccountsController(UserAccountsService _service) {

        this.mService = _service;
    }

    @PostMapping("/api/users/create")
    public UserAccountResponse createUserAccount(@Valid @RequestBody UserAccountRequest _request) {

        return mService.createUserAccount(_request);
    }
}
