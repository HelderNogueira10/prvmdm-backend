package com.privguard.mdm.server.enrollment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.privguard.mdm.server.operations.OperationResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    
    private EnrollmentService mService;

    public EnrollmentController(EnrollmentService _service) {

        this.mService = _service;
    }

    @PostMapping("/validateToken")
    public EnrollmentResponse validateToken(@Valid @RequestBody TokenEnrollmentRequest _request) {

        return mService.validateEnrollmentToken(_request);
    }

    @PostMapping("/confirm")
    public OperationResponse confirmEnrollment(@Valid @RequestBody EnrollmentRequest _request) {

        return mService.confirmEnrollment(_request);
    }
}
