package com.privguard.mdm.server.heartbeat;

import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.security.JwtService;
import org.apache.juli.logging.Log;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/commands")
public class HeartbeatController {

    private HeartbeatService mService;

    public HeartbeatController(HeartbeatService _service) {

        this.mService = _service;
    }

    @PostMapping("/heartbeat")
    public HeartbeatResponse onHeartbeatReceived(@Valid @RequestBody HeartbeatRequest _req, Authentication _authentication) {

        return mService.onHeartbeatReceived(_req, (AuthenticatedAccount) _authentication.getPrincipal());

    }
}
