package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "apps")
public class AppEntity extends BaseEntity {

    @NotNull private String name;
    @NotNull private String packageName;
    @NotNull private String description;

    public String getName() { return name; }
    public String getPackageName() { return packageName; }
    public String getDescription() { return description; }

    public void setName(String _name) { this.name = _name; }
    public void setPackageName(String _packageName) { this.packageName = _packageName; }
    public void setDescription(String _description) { this.description = _description; }
}
