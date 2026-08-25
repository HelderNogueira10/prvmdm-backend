package com.privguard.mdm.server.provisioning;

import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.privguard.mdm.server.global.services.QRCodeService;
import com.privguard.mdm.server.operations.OperationResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/provisioning")
public class ProvisioningController {
    
    private final ProvisioningService provisioningService;
    private final QRCodeService qrCodeService;
    private final ObjectMapper objectMapper;

    public ProvisioningController(
            ProvisioningService provisioningService,
            QRCodeService qrCodeService) {

        this.provisioningService = provisioningService;
        this.qrCodeService = qrCodeService;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/get/all")
    public GetProvisioningSchemasResponse fetchSchemas(Authentication _auth) {

        return provisioningService.fetchSchemas((AuthenticatedAccount) _auth.getPrincipal());
    }

    @GetMapping("/{_name}/json")
    public ProvisioningResponse getEnrollment(@PathVariable String _name) {
        return provisioningService.getEnrollment(_name);
    }

    @GetMapping(
        value="/{_name}/qr",
        produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
    public byte[] qr(@PathVariable String _name) throws Exception {

        ProvisioningResponse response = provisioningService.getEnrollment(_name);
        String json = objectMapper.writeValueAsString(response);
        return qrCodeService.generateQRCode(json);
    }

    @PostMapping("/create")
    public OperationResponse createProvisioningSchema(@Valid @RequestBody ProvisioningRequest _request) {

        return provisioningService.createSchema(_request);
    }

    @GetMapping("/{_name}/delete")
    public OperationResponse deleteProvisioningSchema(@PathVariable String _name) {

        return provisioningService.deleteSchema(_name);
    }
}
