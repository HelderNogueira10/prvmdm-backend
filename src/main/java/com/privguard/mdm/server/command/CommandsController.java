package com.privguard.mdm.server.command;

import com.privguard.mdm.server.command_types.GetCommandTypesResponse;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/paged")
    public GetPagedCommandsResponse getPagedCommands(@RequestParam Integer page, @RequestParam Integer limit, Authentication _auth) {

        return mService.getPagedCommands(page, limit, (AuthenticatedAccount) _auth.getPrincipal());
    }


}
