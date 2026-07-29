package com.privguard.mdm.server.server_health;

import com.privguard.mdm.server.ServerApplication;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerHealthController {
    
    private final ServerHealthService mService;

    public ServerHealthController(ServerHealthService _service) {

        this.mService = _service;
    }

    @GetMapping("/api/health")
    public ServerHealthResponse onHealthCheck() { 

        ServerHealthStatus status = ServerHealthStatus.OPTIMAL;
        return mService.getHealthStatus(status);
    }
}
