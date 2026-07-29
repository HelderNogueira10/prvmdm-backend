package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.operations.OperationResponse;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class FetchAppFilesResponse extends OperationResponse {

    @NotNull private String packageName;
    @NotNull private String packageVersion;
    @NotNull private List<AppFileResponse> files;

    public void setPackageName(String _packageName) { this.packageName = _packageName; }
    public void setPackageVersion(String _packageVersion) { this.packageVersion = _packageVersion; }
    public void setFiles(List<AppFileResponse> _files) { this.files = _files; }

    public String getPackageName() { return packageName; }
    public String getPackageVersion() { return packageVersion; }
    public List<AppFileResponse> getFiles() { return files; }
}
