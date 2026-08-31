package com.privguard.mdm.server.device_accounts;

import com.privguard.mdm.server.global.Constants;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_PREFIX + "/device_accounts")
public class DeviceAccountController {

    private DeviceAccountsService mService;

    public DeviceAccountController(DeviceAccountsService _mService) {

        this.mService = _mService;
    }

    @GetMapping("/get_hostnames")
    public GetHostnamesResponse getHostnames(Authentication _auth) {

        return mService.getHostnames((AuthenticatedAccount) _auth.getPrincipal());
    }

    @GetMapping("/validHostname")
    public OperationResponse validHostname(@RequestParam String hostname, Authentication _auth) {

        return mService.validHostname(hostname, (AuthenticatedAccount) _auth.getPrincipal());
    }
}
