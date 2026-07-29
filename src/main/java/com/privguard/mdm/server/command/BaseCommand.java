package com.privguard.mdm.server.command;

public class BaseCommand {
    
    private Long id;
    private CommandType type;
    private String extraPayload;

    public void setId(Long _id) { this.id = _id; }
    public void setType(CommandType _type) { this.type = _type; }
    public void setExtraPayload(String _payload) { this.extraPayload = _payload; }

    public Long getId() { return id; }
    public CommandType getType() { return type; }
    public String getExtraPayload() { return extraPayload; }
}
