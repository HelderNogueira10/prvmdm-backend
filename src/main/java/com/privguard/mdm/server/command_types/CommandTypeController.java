package com.privguard.mdm.server.command_types;

import com.privguard.mdm.server.global.Constants;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_PREFIX + "/command_types")
public class CommandTypeController {

    private CommandTypeService mService;

    public CommandTypeController(CommandTypeService _mService) {

        this.mService = _mService;
    }

    @GetMapping("/get")
    public GetCommandTypesResponse getTypes(Authentication _auth) {

        return mService.getTypes((AuthenticatedAccount)_auth.getPrincipal());
    }
}
