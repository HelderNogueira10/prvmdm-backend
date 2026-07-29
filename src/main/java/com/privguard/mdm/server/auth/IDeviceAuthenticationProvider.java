package com.privguard.mdm.server.auth;

import com.privguard.mdm.server.account.AccountEntity;

public interface IDeviceAuthenticationProvider {

    AccountEntity authenticate(DeviceAuthenticationRequest _request);
}
