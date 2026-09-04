package com.privguard.mdm.server.provisioning;

import com.privguard.mdm.server.global.Constants;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.privguard.mdm.server.global.services.QRCodeService;
import com.privguard.mdm.server.operations.OperationResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_PREFIX + "/provisioning")
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

    @GetMapping("/json")
    public ProvisioningResponse getEnrollment(@RequestParam Long schema, @RequestParam String hostname) {
        return provisioningService.getEnrollment(schema, hostname);
    }

    @GetMapping(
        value="/qr",
        produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
    public byte[] qr(@RequestParam Long schema, @RequestParam String hostname) throws Exception {

        ProvisioningResponse response = provisioningService.getEnrollment(schema, hostname);
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
