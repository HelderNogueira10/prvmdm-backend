package com.privguard.mdm.server.command;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class CommandRequest {

    @NotNull private Long deviceAccountId;
    @NotNull private Long type;
    private String payload;

    public Long getType() { return type; }
    public String getPayload() { return payload; }
    public Long getDeviceAccountId() { return deviceAccountId; }
}
