package com.privguard.mdm.server.device_accounts;

import com.privguard.mdm.server.account.AccountEntity;

import jakarta.validation.constraints.NotNull;

public class DeviceAccountRequest {
    
    @NotNull private String imei;
    @NotNull private String secret;
    @NotNull private AccountEntity account;

    public String getSecret() { return secret; }
    public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

    public void setSecret(String _secret) { this.secret = _secret; }
	public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
    
}
