package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.app_files.AppFileEntity;
import com.privguard.mdm.server.app_files.AppFilesRepository;
import com.privguard.mdm.server.app_versions.AppVersionEntity;
import com.privguard.mdm.server.app_versions.AppVersionsRepository;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jdk.dynalink.Operation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppsService {

    private final AppVersionsRepository appVersionsRepository;
    private final AppFilesRepository appFilesRepository;
    private AppsRepository appsRepository;

    public AppsService(AppsRepository _appsRepository, AppVersionsRepository appVersionsRepository, AppFilesRepository appFilesRepository) {

        this.appsRepository = _appsRepository;
        this.appVersionsRepository = appVersionsRepository;
        this.appFilesRepository = appFilesRepository;
    }

    public FetchAppFilesResponse getAppFiles(FetchAppFilesRequest _request, AuthenticatedAccount _authedAccount) {

        FetchAppFilesResponse response = new FetchAppFilesResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

         /*   if(_authedAccount.getType() != AccountTypes.ANDROID_ACCOUNT)
                throw new RuntimeException("you cannot request apk installation for a not android device -)");
*/
            AppEntity app = appsRepository.findByPackageName(_request.getPackageName()).orElseThrow(
                    () -> new RuntimeException("Requested Package Name Not Found!"));

            AppVersionEntity appVersion = appVersionsRepository.findByVersionCodeAndApp(_request.getPackageVersion(), app).orElseThrow(
                    () -> new RuntimeException("Request App Version Not Found!"));

            List<AppFileResponse> filesResponse = new ArrayList<>();
            List<AppFileEntity> appFiles = appFilesRepository.findAllByAppVersion(appVersion);
            for(AppFileEntity file : appFiles) {

                AppFileResponse fileResponse = new AppFileResponse();
                fileResponse.setFilename(file.getFilename());
                fileResponse.setChecksum(file.getChecksum());
                fileResponse.setLength(file.getFileLength());
                filesResponse.add(fileResponse);
            }

            response.setFiles(filesResponse);
            response.setPackageName(app.getPackageName());
            response.setPackageVersion(appVersion.getVersionName());

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Fetching App Files Exception: " + _e.getMessage());
        }

        return response;
    }

    public OperationResponse add(AddAppRequest _request, AuthenticatedAccount _authedAccount) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            if(_authedAccount.getType() != AccountTypes.USER_ACCOUNT)
                throw new RuntimeException("your account type is not allowed to perform this action");

            //check if has privileges

            AppEntity app = new AppEntity();
            app.setName(_request.getName());
            app.setDescription(_request.getDescription());
            app.setPackageName(_request.getPackageName());
            appsRepository.save(app);

            response.setMessage("OK");
            response.setStatus(OperationStatus.SUCCESS);
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Adding App Exception: " + _e.getMessage());
        }

        return response;
    }
}
