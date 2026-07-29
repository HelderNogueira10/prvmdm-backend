package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;

public interface IUserAuthenticationProvider {

    AccountEntity authenticate(UserAuthenticationRequest _request);
}
