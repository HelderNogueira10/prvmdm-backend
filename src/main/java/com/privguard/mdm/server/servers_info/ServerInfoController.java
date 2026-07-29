package com.privguard.mdm.server.servers_info;

import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servers_info")
public class ServerInfoController {

    private final ServersInfoService serversInfoService;

    public ServerInfoController(ServersInfoService serversInfoService) {

        this.serversInfoService = serversInfoService;
    }

    @GetMapping("/delete/{_serverId}")
    public OperationResponse deleteServer(@PathVariable Long _serverId, Authentication _auth) {

        return serversInfoService.deleteServer(_serverId, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @PostMapping("/update")
    public OperationResponse updateServer(@Valid @RequestBody ServerInfoRequest _request, Authentication _auth) {

        return serversInfoService.updateServer(_request, (AuthenticatedAccount) _auth.getPrincipal());
    }
}
