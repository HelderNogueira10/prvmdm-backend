package com.privguard.mdm.server.enrollment;

import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/all")
    public GetEnrollmentsGLPIResponse getAllEnrollments(Authentication _auth) {

        return mService.getAllEnrollments((AuthenticatedAccount) _auth.getPrincipal());
    }
}
