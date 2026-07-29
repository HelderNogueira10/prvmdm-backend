package com.privguard.mdm.server.apps;

import jakarta.validation.constraints.NotNull;

public class AppFileResponse {

    @NotNull private String filename;
    @NotNull private String checksum;
    @NotNull private Long length;

    public Long getLength() { return length; }
    public String getFilename() { return filename; }
    public String getChecksum() { return checksum; }

    public void setLength(Long _length) { this.length = _length; }
    public void setFilename(String _filename) { this.filename = _filename; }
    public void setChecksum(String _checksum) { this.checksum = _checksum; }
}
