package com.privguard.mdm.server.devices;

import com.privguard.mdm.server.ServerApplication;
import com.privguard.mdm.server.global.Constants;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_PREFIX + "/devices")
public class DevicesController {

    private final DevicesService devicesService;

    public DevicesController(DevicesService devicesService) {
        this.devicesService = devicesService;
    }

    @GetMapping("/get")
    public FetchBaseDeviceResponse getBaseDevice(@RequestParam String uuid, Authentication _auth) {

        return devicesService.getBase(uuid, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @GetMapping("/paged")
    public FetchBaseDevicesResponse getBaseDevices(@RequestParam Integer pageId,
                                                   @RequestParam Integer count,
                                                   Authentication _auth) {

        return devicesService.getBaseDevices(pageId, count, (AuthenticatedAccount) _auth.getPrincipal());
    }
}
