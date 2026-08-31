package com.privguard.mdm.server.provisioning;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProvisioningExtrasResponse {
    
    @JsonProperty("enrollmentToken")
    private String enrollmentToken;

    @JsonProperty("tokenId")
    private String tokenId;

    @JsonProperty("hostname")
    private String hostname;

    public String getEnrollmentToken() {
        return enrollmentToken;
    }

    public void setEnrollmentToken(String enrollmentToken) {
        this.enrollmentToken = enrollmentToken;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
