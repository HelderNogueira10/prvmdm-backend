package com.privguard.mdm.server.managers;

import org.springframework.stereotype.Component;

import com.privguard.mdm.server.user_accounts.UserAccountsService;

@Component
public class ServicesManager {
 
    private final UserAccountsService userAccountsService;
    private static ServicesManager INSTANCE;

    public ServicesManager(UserAccountsService _userAccountsService) {

        if(INSTANCE == null)
            INSTANCE = this;

        this.userAccountsService = _userAccountsService;
    }

    public static ServicesManager getInstance() { return INSTANCE; }

    public UserAccountsService getUserAccountsService() {
        return userAccountsService;
    }
}
