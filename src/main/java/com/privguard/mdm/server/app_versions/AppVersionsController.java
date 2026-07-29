package com.privguard.mdm.server.app_versions;

import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apps/versions")
public class AppVersionsController {

    private final AppVersionsService appVersionsService;

    public AppVersionsController(AppVersionsService appVersionsService) {
        this.appVersionsService = appVersionsService;
    }

    @PostMapping("/add")
    public OperationResponse add(@Valid @RequestBody AddAppVersionRequest _request, Authentication _auth) {

        AuthenticatedAccount account = (AuthenticatedAccount) _auth.getPrincipal();
        return appVersionsService.add(_request, account);
    }
}
