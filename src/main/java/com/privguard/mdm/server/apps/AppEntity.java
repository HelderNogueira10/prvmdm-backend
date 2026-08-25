package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "apps")
public class AppEntity extends BaseEntity {

    @NotNull private String name;
    @NotNull private String packageName;
     private String description;
    @Column(nullable = false) private Integer installsCount = 0;
    @Column(nullable = false) private Integer uninstallsCount = 0;
    private String iconUrl;

    public String getName() { return name; }
    public String getPackageName() { return packageName; }
    public String getDescription() { return description; }

    public void setName(String _name) { this.name = _name; }
    public void setPackageName(String _packageName) { this.packageName = _packageName; }
    public void setDescription(String _description) { this.description = _description; }

    public Integer getInstallsCount() {
        return installsCount;
    }

    public void setInstallsCount(Integer installsCount) {
        this.installsCount = installsCount;
    }

    public Integer getUninstallsCount() {
        return uninstallsCount;
    }

    public void setUninstallsCount(Integer uninstallsCount) {
        this.uninstallsCount = uninstallsCount;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
