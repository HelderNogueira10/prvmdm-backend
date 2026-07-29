package com.privguard.mdm.server.app_files;

import com.privguard.mdm.server.apps.AppsService;
import com.privguard.mdm.server.apps.FetchAppFilesRequest;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apps/files")
public class AppFilesController {

    private final AppFilesService appFilesService;
    private final AppsService appsService;

    public AppFilesController(AppFilesService appFilesService, AppsService appsService) {
        this.appFilesService = appFilesService;
        this.appsService = appsService;
    }

    @PostMapping("/add")
    public OperationResponse add(@Valid @RequestBody AddAppFileRequest _request, Authentication _auth) {

        AuthenticatedAccount account = (AuthenticatedAccount) _auth.getPrincipal();
        return appFilesService.add(_request, account);
    }

    @PostMapping("/fetch")
    public OperationResponse fetchFiles(@Valid @RequestBody FetchAppFilesRequest _request, Authentication _auth) {

        AuthenticatedAccount account = (AuthenticatedAccount) _auth.getPrincipal();
        return appsService.getAppFiles(_request, account);
    }
}
