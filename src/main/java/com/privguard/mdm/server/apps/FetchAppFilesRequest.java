package com.privguard.mdm.server.apps;

import jakarta.validation.constraints.NotNull;

public class FetchAppFilesRequest {

    @NotNull private String packageName;
    @NotNull private String packageVersion;

    public String getPackageName() { return packageName; }
    public String getPackageVersion() { return packageVersion; }

    public void setPackageName(String _packageName) { this.packageName = _packageName; }
    public void setPackageVersion(String _packageVersion) { this.packageVersion = _packageVersion; }
}
