package com.privguard.mdm.server.stats;

import com.privguard.mdm.server.global.Constants;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_PREFIX + "/stats")
public class StatsController {

    private StatsService mService;

    public StatsController(StatsService _mService) {

        this.mService = _mService;
    }

    @GetMapping("/devices")
    public DeviceStatsResponse getDeviceStats(Authentication _auth) {

        return mService.getDeviceStats((AuthenticatedAccount) _auth.getPrincipal());
    }

    @GetMapping("/provisioning")
    public ProvisioningStatsResponse getProvisionStats(Authentication _auth) {

        return mService.getProvisionStats((AuthenticatedAccount) _auth.getPrincipal());
    }

    @GetMapping("/commands")
    public CommandsStatsResponse getCommandStats(Authentication _auth) {

        return mService.getCommandStats((AuthenticatedAccount) _auth.getPrincipal());
    }
}
