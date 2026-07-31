package com.privguard.mdm.server.device_accounts;

import com.privguard.mdm.server.operations.OperationResponse;

public class DeviceAccountResponse extends OperationResponse {

    private String accountUuid;
    private String imei;

    public String getAccountUuid() { return accountUuid; }
    public void setAccountUuid(String _accountUuid) { this.accountUuid = _accountUuid; }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
