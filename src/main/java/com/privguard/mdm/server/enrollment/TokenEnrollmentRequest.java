package com.privguard.mdm.server.enrollment;

public class TokenEnrollmentRequest {
    
    private String enrollmentToken;
    private String emei;
    private Long id;

    public String getEnrollmentToken() {
        return enrollmentToken;
    }

    public void setEnrollmentToken(String enrollmentToken) {
        this.enrollmentToken = enrollmentToken;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmei() {
        return emei;
    }

    public void setEmei(String emei) {
        this.emei = emei;
    }
}
