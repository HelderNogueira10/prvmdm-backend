package com.privguard.mdm.server.app_versions;

import com.privguard.mdm.server.apps.AppEntity;
import com.privguard.mdm.server.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import tools.jackson.core.ObjectReadContext;

@Entity
@Table(name = "app_versions")
public class AppVersionEntity extends BaseEntity {

    @NotNull @ManyToOne @JoinColumn(name = "app_id") private AppEntity app;
    @NotNull private String versionCode;
    @NotNull private String versionName;

    public void setApp(AppEntity _app) { this.app = _app; }
    public void setVersionCode(String _versionCode) { this.versionCode = _versionCode; }
    public void setVersionName(String _versionName) { this.versionName = _versionName; }

    public AppEntity getApp() { return app; }
    public String getVersionCode() { return versionCode; }
    public String getVersionName() { return versionName; }
}
