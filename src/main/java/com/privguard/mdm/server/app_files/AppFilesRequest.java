package com.privguard.mdm.server.app_files;

import com.privguard.mdm.server.app_versions.AppVersionEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class AppFilesRequest {

    @NotNull private AppVersionEntity appVersion;
    @NotNull private String filename;
    @NotNull private String checksum;

    public AppVersionEntity getAppVersion() { return appVersion; }
    public String getFilename() { return filename; }
    public String getChecksum() { return checksum; }

    public void setAppVersion(AppVersionEntity _appVersion) { this.appVersion = _appVersion; }
    public void setFilename(String _filename) { this.filename = _filename; }
    public void setChecksum(String _checksum) { this.checksum = _checksum; }

}
