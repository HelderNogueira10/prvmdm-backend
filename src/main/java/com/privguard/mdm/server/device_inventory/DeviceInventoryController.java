package com.privguard.mdm.server.device_inventory;

import com.privguard.mdm.server.global.Constants;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_PREFIX + "/device_inventory")
public class DeviceInventoryController {

    private DeviceInventoryService mService;

    public DeviceInventoryController(DeviceInventoryService _service) {

        this.mService = _service;
    }

    @PostMapping("/update/basic")
    public OperationResponse updateBasic(@Valid @RequestBody BasicDeviceInventoryRequest _request, Authentication _auth) {

        return mService.updateBasic(_request, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @PostMapping("/update/partial")
    public OperationResponse updatePartial(@Valid @RequestBody PartialDeviceInventoryRequest _request, Authentication _auth) {

        return mService.updatePartial(_request, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @PostMapping("/update/full")
    public OperationResponse updateFull(@Valid @RequestBody FullDeviceInventoryRequest _request, Authentication _auth) {

        return mService.updateFull(_request, (AuthenticatedAccount) _auth.getPrincipal());
    }
}
