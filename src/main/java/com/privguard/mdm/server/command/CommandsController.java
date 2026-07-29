package com.privguard.mdm.server.command;

import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commands")
public class CommandsController {
    
    private CommandsService mService;

    public CommandsController(CommandsService _service) {

        this.mService = _service;
    }

    @PostMapping("/add")
    public OperationResponse addCommand(@Valid @RequestBody CommandRequest _request, Authentication _auth) {

        AuthenticatedAccount authedAccount = (AuthenticatedAccount) _auth.getPrincipal();
        return mService.addCommand(_request, authedAccount);
    }

    @PostMapping("/update")
    public OperationResponse updateCommand(@Valid @RequestBody CommandUpdateRequest _request, Authentication _auth) {

        AuthenticatedAccount authAccount = (AuthenticatedAccount) _auth.getPrincipal();
        return mService.updateCommand(_request, authAccount);
    }
}
