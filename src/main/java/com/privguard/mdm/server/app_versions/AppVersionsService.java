package com.privguard.mdm.server.app_versions;

import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.apps.AppsRepository;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.stereotype.Service;

@Service
public class AppVersionsService {

    private final AppsRepository appsRepository;
    private final AppVersionsRepository appVersionsRepository;

    public AppVersionsService(AppsRepository appsRepository, AppVersionsRepository appVersionsRepository) {
        this.appsRepository = appsRepository;
        this.appVersionsRepository = appVersionsRepository;
    }

    public OperationResponse add(AddAppVersionRequest _request, AuthenticatedAccount _account) {

        OperationResponse response  = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            if(_account.getType() != AccountTypes.USER_ACCOUNT)
                throw new RuntimeException("your account does not have enough privileges");

            //check permissions
            AppVersionEntity appVersion = new AppVersionEntity();
            appVersion.setApp(appsRepository.getById(_request.getAppId()));
            appVersion.setVersionName(_request.getVersionName());
            appVersion.setVersionCode(_request.getVersionCode());
            appVersionsRepository.save(appVersion);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Adding App Version Exception: " + _e.getMessage());
        }

        return response;
    }
}
