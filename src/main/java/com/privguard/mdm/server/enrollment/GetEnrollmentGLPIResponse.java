package com.privguard.mdm.server.enrollment;

public class GetEnrollmentGLPIResponse {

    private Long tokenId;
    private String status;
    private String hostname;
    private String startedAt;
    private String finishedAt;
    private String schemaUsed;
    private String enrollmentStatus;

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getSchemaUsed() {
        return schemaUsed;
    }

    public void setSchemaUsed(String schemaUsed) {
        this.schemaUsed = schemaUsed;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
