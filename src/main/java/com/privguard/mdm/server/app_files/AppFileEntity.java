package com.privguard.mdm.server.app_files;

import com.privguard.mdm.server.app_versions.AppVersionEntity;
import com.privguard.mdm.server.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "app_files")
public class AppFileEntity extends BaseEntity {

    @NotNull @ManyToOne @JoinColumn(name = "app_version_id") private AppVersionEntity appVersion;
    @NotNull private String filename;
    @NotNull private String checksum;
    @NotNull private Long fileLength;
    @NotNull private String realPath;

    public void setAppVersion(AppVersionEntity _appVersion) { this.appVersion = _appVersion; }
    public void setFileLength(Long _fileLength) { this.fileLength = _fileLength; }
    public void setFilename(String _filename) { this.filename = _filename; }
    public void setChecksum(String _checksum) { this.checksum = _checksum; }
    public void setRealPath(String _realPath) { this.realPath = _realPath; }

    public AppVersionEntity getAppVersion() { return appVersion; }
    public Long getFileLength() { return fileLength; }
    public String getFilename() { return filename; }
    public String getChecksum() { return checksum; }
    public String getRealPath() { return realPath; }
}
