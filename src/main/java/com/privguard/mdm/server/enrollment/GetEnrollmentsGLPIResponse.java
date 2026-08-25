package com.privguard.mdm.server.enrollment;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class GetEnrollmentsGLPIResponse extends OperationResponse {

    private List<GetEnrollmentGLPIResponse> enrollments;

    public List<GetEnrollmentGLPIResponse> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<GetEnrollmentGLPIResponse> enrollments) {
        this.enrollments = enrollments;
    }
}
