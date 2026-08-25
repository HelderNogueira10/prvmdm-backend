package com.privguard.mdm.server.app_versions;

import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.app_files.AppFileEntity;
import com.privguard.mdm.server.app_files.AppFilesRepository;
import com.privguard.mdm.server.app_files.AppFilesService;
import com.privguard.mdm.server.app_files.GetApplicationFileResponse;
import com.privguard.mdm.server.apps.*;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppVersionsService {

    private final AppsRepository appsRepository;
    private final AppVersionsRepository appVersionsRepository;
    private final AppFilesRepository appFilesRepository;

    public AppVersionsService(AppsRepository appsRepository, AppVersionsRepository appVersionsRepository, AppFilesRepository appFilesRepository) {
        this.appsRepository = appsRepository;
        this.appVersionsRepository = appVersionsRepository;
        this.appFilesRepository = appFilesRepository;
    }

    public OperationResponse delete(Integer _versionId, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();

        try {

            //check perms
            AppVersionEntity dbVersion = appVersionsRepository.findById(_versionId.longValue()).orElseThrow(
                    () -> new RuntimeException("app version  not found: " + _versionId));

            appFilesRepository.deleteAll(appFilesRepository.findAllByAppVersion(dbVersion));
            appVersionsRepository.delete(dbVersion);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setMessage("AppVersionsService->delete: " + _e.getMessage());
        }

        return response;
    }

    public AppVersionEntity findLastest(AppEntity _app) {

        List<AppVersionEntity> dbVersions = appVersionsRepository.findAllByAppOrderByCreatedAtDesc(_app);
        return dbVersions.isEmpty() ? null : dbVersions.getFirst();
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

    public AppVersionResponse get(Long _versionId, AuthenticatedAccount _account) {

        AppVersionResponse response = new AppVersionResponse();

        try {

            AppVersionEntity dbVersion = appVersionsRepository.findById(_versionId.longValue()).orElseThrow(
                    () -> new RuntimeException("app does not exists"));

            response.setId(dbVersion.getId());
            response.setCreatedAt(dbVersion.getCreatedAt().toString());
            response.setUpdatedAt(dbVersion.getUpdatedAt().toString());
            response.setAppName(dbVersion.getApp().getName());
            response.setVersionName(dbVersion.getVersionName());
            response.setVersionCode(dbVersion.getVersionCode());

            List<GetApplicationFileResponse> fileResponses = new ArrayList<>();
            List<AppFileEntity> dbFiles = appFilesRepository.findAllByAppVersion(dbVersion);
            for(AppFileEntity file : dbFiles) {

                GetApplicationFileResponse fileResponse = new GetApplicationFileResponse();
                fileResponse.setFilename(file.getFilename());
                fileResponse.setChecksum(file.getChecksum());
                fileResponse.setId(file.getId());
                fileResponse.setFileLength(file.getFileLength());
                fileResponse.setCreatedAt(file.getCreatedAt());
                fileResponse.setUpdatedAt(file.getUpdatedAt());
                fileResponse.setRealPath(file.getRealPath());
                fileResponse.setVersionId(file.getAppVersion().getId());
                fileResponse.setStatus(OperationStatus.SUCCESS);
                fileResponse.setMessage("OK");
                fileResponses.add(fileResponse);
            }
            response.setFiles(fileResponses);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) { response.setMessage("AppVersionsService->get: " + _e.getMessage()); }
        return response;
    }

    public GetApplicationVersionsResponse getAll(Long _appId, AuthenticatedAccount _account) {

        GetApplicationVersionsResponse response = new GetApplicationVersionsResponse();

        try {

            //check perms
            AppEntity dbApp = appsRepository.findById(_appId).orElseThrow(
                    () -> new RuntimeException("application not found."));

            List<AppVersionResponse> appVersions = new ArrayList<>();
            List<AppVersionEntity> dbVersions = appVersionsRepository.findAllByApp(dbApp);

            for(AppVersionEntity version : dbVersions)
                appVersions.add(get(version.getId(), _account));

            response.setAppId(dbApp.getId());
            response.setVersions(appVersions);
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) {

            response.setMessage("AppVersionsService->getAll: " + _e.getMessage());
        }

        return response;
    }
}
