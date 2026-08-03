package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;

public interface IAPIAuthenticationProvider {

    AccountEntity authenticate(ApiAuthenticationRequest _request);
}
