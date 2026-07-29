package com.privguard.mdm.server.app_files;

import com.beust.ah.A;
import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.app_versions.AppVersionEntity;
import com.privguard.mdm.server.app_versions.AppVersionsRepository;
import com.privguard.mdm.server.apps.AddAppRequest;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AppFilesService {

    private final AppVersionsRepository appVersionsRepository;
    private AppFilesRepository appFilesRepository;

    public AppFilesService(AppFilesRepository _appFilesRepository, AppVersionsRepository appVersionsRepository) {

        this.appFilesRepository = _appFilesRepository;
        this.appVersionsRepository = appVersionsRepository;
    }

    public OperationResponse add(AddAppFileRequest _request, AuthenticatedAccount _authedAccount) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            if(_authedAccount.getType() != AccountTypes.USER_ACCOUNT)
                throw new RuntimeException("invalid account");

            AppFileEntity appFile = new AppFileEntity();
            appFile.setFilename(_request.getFilename());
            appFile.setAppVersion(appVersionsRepository.findById(_request.getAppVersionId()).orElseThrow(
                    () -> new RuntimeException("app version does not exists")));
            appFile.setChecksum("asdsa");
            appFile.setRealPath("adas");
            appFile.setFileLength(Long.valueOf(32));
            appFilesRepository.save(appFile);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("App File Added Successfully");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Adding File Exception: " + _e.getMessage());
        }

        return response;
    }
}
