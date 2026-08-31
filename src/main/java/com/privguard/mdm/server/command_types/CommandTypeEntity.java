package com.privguard.mdm.server.command_types;

import com.privguard.mdm.server.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "command_types")
public class CommandTypeEntity extends BaseEntity {

    @NotNull private String type;
    @NotNull private Long timeout;

    public String getType() { return type; }
    public Long getTimeout() { return timeout; }

    public void setType(String _type) { this.type = _type; }
    public void setTimeout(Long _timeout) { this.timeout = _timeout; }

}
