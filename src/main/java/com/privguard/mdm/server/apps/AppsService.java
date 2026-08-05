package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.app_files.AppFileEntity;
import com.privguard.mdm.server.app_files.AppFilesRepository;
import com.privguard.mdm.server.app_versions.AppVersionEntity;
import com.privguard.mdm.server.app_versions.AppVersionResponse;
import com.privguard.mdm.server.app_versions.AppVersionsRepository;
import com.privguard.mdm.server.command.CommandStatus;
import com.privguard.mdm.server.command.CommandType;
import com.privguard.mdm.server.command.CommandsRepository;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jdk.dynalink.Operation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppsService {

    private final AppVersionsRepository appVersionsRepository;
    private final AppFilesRepository appFilesRepository;
    private final CommandsRepository commandsRepository;
    private AppsRepository appsRepository;

    public AppsService(AppsRepository _appsRepository, AppVersionsRepository appVersionsRepository, AppFilesRepository appFilesRepository, CommandsRepository commandsRepository) {

        this.appsRepository = _appsRepository;
        this.appVersionsRepository = appVersionsRepository;
        this.appFilesRepository = appFilesRepository;
        this.commandsRepository = commandsRepository;
    }

    public OperationResponse delete(Integer _id, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            if(_account.getType() == AccountTypes.ANDROID_ACCOUNT)
                throw new RuntimeException("your account does not have sufficient perms...");

            AppEntity app = appsRepository.findById(_id.longValue()).orElseThrow(
                    () -> new RuntimeException("app does not exists..."));
            response.setMessage(app.getName());

            List<AppVersionEntity> appVersions = appVersionsRepository.findAllByApp(app);
            for(AppVersionEntity version : appVersions)
                appFilesRepository.deleteAll(appFilesRepository.findAllByAppVersion(version));

            appVersionsRepository.deleteAll(appVersions);
            appsRepository.delete(app);

            response.setStatus(OperationStatus.SUCCESS);
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Application Delete Exception: " + _e.getMessage());
        }

        return response;
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

    public GetAppResponse getApp(Integer _appId, AuthenticatedAccount _account) {

        GetAppResponse response = new GetAppResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            if(_account.getType() == AccountTypes.ANDROID_ACCOUNT)
                throw new RuntimeException("your account does not have enough permissions...");

            AppEntity app = appsRepository.findById(_appId.longValue()).orElseThrow(
                    () -> new RuntimeException("app id does not exists..."));

            AppVersionEntity appVersion = appVersionsRepository.findByVersionCodeAndApp("latest", app).orElseThrow(
                    () ->new RuntimeException("app version has not been found..."));

            List<AppFileEntity> appFiles = appFilesRepository.findAllByAppVersion(appVersion);

            Integer appVersionsCount = Integer.parseInt(String.valueOf(appVersionsRepository.findAllByApp(app).size()));
            Integer associatedFilesCount = Integer.parseInt(String.valueOf(appFiles.size()));

            Long appTotalSize = Long.valueOf(0);
            for(AppFileEntity appFile: appFiles)
                appTotalSize += appFile.getFileLength();

            AppVersionResponse versionResponse = new AppVersionResponse();
            versionResponse.setVersionCode(appVersion.getVersionCode());
            versionResponse.setVersionName(appVersion.getVersionName());
            versionResponse.setAppName(app.getName());

            response.setId(Integer.parseInt(String.valueOf(app.getId())));
            response.setCreatedAt(app.getCreatedAt());
            response.setName(app.getName());
            response.setPackageName(app.getPackageName());
            response.setDescription(app.getDescription());
            response.setAppFilesCount(associatedFilesCount);
            response.setVersion(versionResponse);
            response.setTotalAppSize(appTotalSize);
            response.setAppVersionsCount(appVersionsCount);
            response.setInstallsCount(app.getInstallsCount());
            response.setUninstallsCount(app.getUninstallsCount());

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Getting App Exception: " + _e.getMessage());
        }

        return response;
    }

    public GetAppsResponse getAll(AuthenticatedAccount _account) {

        GetAppsResponse response = new GetAppsResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            if(_account.getType() == AccountTypes.ANDROID_ACCOUNT)
                throw new RuntimeException("your account does not have enough perms...");

            List<AppEntity> apps = appsRepository.findAll();
            List<GetAppResponse> appResponses = new ArrayList<>();

            for(AppEntity app : apps)
                appResponses.add(getApp(Integer.parseInt(String.valueOf(app.getId())), _account));

            response.setAppsCount(apps.size());
            response.setAppsResponse(appResponses);
            response.setTimestamp(LocalDateTime.now());

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Getting All Apps Exception: " + _e.getMessage());
        }

        return response;
    }

    public AppStatisticsResponse getStatistics(AuthenticatedAccount _account) {

        AppStatisticsResponse response = new AppStatisticsResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            //check if source account has perms
            if(_account.getType() != AccountTypes.SERVICE_ACCOUNT && _account.getType() != AccountTypes.USER_ACCOUNT && _account.getType() != AccountTypes.API_ACCOUNT)
                throw new RuntimeException("insuficient perms...");

            response.setTotalApps(Integer.parseInt(String.valueOf(appsRepository.count())));
            response.setTotalAppFiles(Integer.parseInt(String.valueOf(appFilesRepository.count())));
            response.setTotalAppVersions(Integer.parseInt(String.valueOf(appVersionsRepository.count())));
            response.setPendingInstall(Integer.parseInt(String.valueOf(commandsRepository.findByTypeAndStatus(CommandType.INSTALL_APP, CommandStatus.PENDING).size())));
            response.setFailedInstall(Integer.parseInt(String.valueOf(commandsRepository.findByTypeAndStatus(CommandType.INSTALL_APP, CommandStatus.FAILED).size())));
            response.setTotalAppInstalls(Integer.parseInt(String.valueOf(commandsRepository.findByTypeAndStatus(CommandType.INSTALL_APP, CommandStatus.SUCCESS).size())));
            response.setTotalAppUninstalls(Integer.parseInt(String.valueOf(commandsRepository.findByTypeAndStatus(CommandType.UNINSTALL_APP, CommandStatus.SUCCESS).size())));

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Getting App Statistics Exception: " + _e.getMessage());
        }

        return response;
    }


    public int getTotalApplications() {

        int total = 0;

        try {

            total = Integer.parseInt(String.valueOf(appsRepository.count()));
        }
        catch(Exception _e) { System.out.println("Total Application Error: " + _e.getMessage());}

        return total;
    }
}
