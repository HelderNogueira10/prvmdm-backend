package com.privguard.mdm.server.command;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class CommandResponse {

    private Long id;
    private String accountUuid;
    private String message;
    private String payload;
    private CommandStatus commandStatus;
    private CommandType commandType;

    public void setId(Long _id) { this.id = _id; }
    public void setMessage(String _message) { this.message = _message; }
    public void setAccountUuid(String _accountUuid) { this.accountUuid = _accountUuid; }
    public void setCommandStatus(CommandStatus _status) { this.commandStatus = _status; }
    public void setCommandType(CommandType _type) { this.commandType = _type; }
    public void setPayload(String _payload) { this.payload = _payload; }

    public Long getId() { return id; }
    public String getMessage()  { return message; }
    public String getAccountUuid() { return accountUuid; }
    public CommandStatus getCommandStatus() { return commandStatus; }
    public CommandType getCommandType() { return commandType; }
    public String getPayload() { return payload; }
}
