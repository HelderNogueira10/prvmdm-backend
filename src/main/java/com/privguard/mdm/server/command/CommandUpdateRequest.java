package com.privguard.mdm.server.command;

public class CommandUpdateRequest {

    private Long id;
    private String updateDate;
    private CommandStatus status;

    public Long getId() { return id; }
    public String getUpdateDate() { return updateDate; }
    public CommandStatus getStatus() { return status; }

    public void setId(Long _id) { this.id = _id; }
    public void setStatus(CommandStatus _status) { this.status = _status; }
    public void setUpdateDate(String _updateDate) { this.updateDate = _updateDate; }
}
