package com.privguard.mdm.server.command;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class CommandRequest {

    @NotNull private String deviceAccountUuid;
    @NotNull @Enumerated(EnumType.STRING) private CommandType type;
    private String payload;

    public CommandType getType() { return type; }
    public String getPayload() { return payload; }
    public String getDeviceAccountUuid() { return deviceAccountUuid; }
}
