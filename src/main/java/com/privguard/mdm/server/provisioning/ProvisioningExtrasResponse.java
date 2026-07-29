package com.privguard.mdm.server.provisioning;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProvisioningExtrasResponse {
    
    @JsonProperty("enrollmentToken")
    private String enrollmentToken;

    @JsonProperty("tokenId")
    private String tokenId;

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
}
