package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;

public interface IServiceAuthenticationProvider {

    AccountEntity authenticate(ServiceAuthenticationRequest _request);

}
