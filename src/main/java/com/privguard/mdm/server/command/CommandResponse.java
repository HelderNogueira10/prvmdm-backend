package com.privguard.mdm.server.command;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class CommandResponse {

    private Long id;
    private String accountUuid;
    private String message;
    private String payload;
    private CommandStatus commandStatus;
    private Long commandTypeId;
    private String commandType;
    private Long timeout;

    public void setTimeout(Long timeout) { this.timeout = timeout; }
    public void setId(Long _id) { this.id = _id; }
    public void setMessage(String _message) { this.message = _message; }
    public void setAccountUuid(String _accountUuid) { this.accountUuid = _accountUuid; }
    public void setCommandStatus(CommandStatus _status) { this.commandStatus = _status; }
    public void setCommandTypeId(Long _type) { this.commandTypeId = _type; }
    public void setPayload(String _payload) { this.payload = _payload; }
    public void setCommandType(String commandType) { this.commandType = commandType; }

    public Long getTimeout() { return timeout; }
    public Long getId() { return id; }
    public String getMessage()  { return message; }
    public String getAccountUuid() { return accountUuid; }
    public CommandStatus getCommandStatus() { return commandStatus; }
    public Long getCommandTypeId() { return commandTypeId; }
    public String getPayload() { return payload; }
    public String getCommandType() { return commandType; }
}
