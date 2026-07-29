package com.privguard.mdm.server.user_accounts;

import jakarta.validation.constraints.NotNull;

public class UserAccountResponse {
    
    @NotNull private String userUuid;
    @NotNull private String message;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
