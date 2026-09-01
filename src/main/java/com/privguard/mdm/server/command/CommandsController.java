package com.privguard.mdm.server.command;

import com.privguard.mdm.server.command_types.GetCommandTypesResponse;
import com.privguard.mdm.server.global.Constants;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_PREFIX + "/commands")
public class CommandsController {
    
    private CommandsService mService;

    public CommandsController(CommandsService _service) {

        this.mService = _service;
    }

    @PostMapping("/add")
    public OperationResponse addCommand(@Valid @RequestBody CommandRequest _request, Authentication _auth) {

        return mService.addCommand(_request, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @GetMapping("/paged")
    public GetPagedCommandsResponse getPagedCommands(@RequestParam Integer page, @RequestParam Integer limit, Authentication _auth) {

        return mService.getPagedCommands(page, limit, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @PatchMapping("/update")
    public OperationResponse updateCommand(@Valid @RequestBody CommandUpdateRequest _request, Authentication _auth) {

        return mService.updateCommand(_request, (AuthenticatedAccount) _auth.getPrincipal());
    }
}
