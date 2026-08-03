package com.privguard.mdm.server.api_accounts;

import com.privguard.mdm.server.operations.OperationResponse;
import jakarta.validation.constraints.NotNull;

public class AddApiAccountResponse extends OperationResponse  {

    @NotNull private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
