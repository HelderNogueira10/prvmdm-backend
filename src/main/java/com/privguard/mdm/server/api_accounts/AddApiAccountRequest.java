package com.privguard.mdm.server.api_accounts;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class AddApiAccountRequest {

    @NotNull private String accountUuid;
    @NotNull @Enumerated(EnumType.STRING) private ApiAccountStatus status;

    public ApiAccountStatus getStatus() { return status; }
    public void setStatus(ApiAccountStatus _status) { this.status = _status; }

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }
}
