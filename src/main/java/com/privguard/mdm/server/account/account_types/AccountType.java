package com.privguard.mdm.server.account.account_types;

import com.privguard.mdm.server.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "account_types")
public class AccountType extends BaseEntity {

    @NotNull private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
