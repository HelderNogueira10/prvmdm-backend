package com.privguard.mdm.server.command_types;

import com.privguard.mdm.server.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "command_types")
public class CommandTypeEntity extends BaseEntity {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotNull private String type;
}
