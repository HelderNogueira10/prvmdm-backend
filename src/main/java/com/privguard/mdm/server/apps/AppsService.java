package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.app_files.AppFileEntity;
import com.privguard.mdm.server.app_files.AppFilesRepository;
import com.privguard.mdm.server.app_files.AppFilesService;
import com.privguard.mdm.server.app_versions.*;
import com.privguard.mdm.server.command.CommandStatus;
import com.privguard.mdm.server.command.CommandType;
import com.privguard.mdm.server.command.CommandsRepository;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AppsService {

    private final AppVersionsRepository appVersionsRepository;
    private final AppFilesRepository appFilesRepository;
    private final CommandsRepository commandsRepository;
    private final AppVersionsService appVersionsService;
    private final AppFilesService appFilesService;
    private AppsRepository appsRepository;

    public AppsService(AppsRepository _appsRepository,
                       AppVersionsRepository appVersionsRepository,
                       AppFilesRepository appFilesRepository,
                       CommandsRepository commandsRepository,
                       AppVersionsService appVersionsService,
                       AppFilesService appFilesService) {

        this.appsRepository = _appsRepository;
        this.appFilesService = appFilesService;
        this.appVersionsRepository = appVersionsRepository;
        this.appFilesRepository = appFilesRepository;
        this.commandsRepository = commandsRepository;
        this.appVersionsService = appVersionsService;
    }

    public OperationResponse editApp(EditAppRequest _request, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();

        try {

            //check perms
            AppEntity dbApp = appsRepository.findById(_request.getId()).orElseThrow(
                    () -> new RuntimeException("app not found..."));

            dbApp.setName(_request.getName());
            dbApp.setIconUrl(_request.getIconUrl());
            dbApp.setDescription(_request.getAppDescription());
            dbApp.setPackageName(_request.getPackageName());
            appsRepository.save(dbApp);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setMessage("AppsService->editApp: "+ _request.getId() + ":" + _e.getMessage());
        }

        return response;
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
            app.setIconUrl(_request.getIconUrl());
            app.setDescription(Objects.requireNonNullElse(_request.getAppDescription(), _request.getName()));
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

    public GetApplicationResponse getApp(Long _appId, AuthenticatedAccount _account) {

        GetApplicationResponse response = new GetApplicationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            if(_account.getType() == AccountTypes.ANDROID_ACCOUNT)
                throw new RuntimeException("your account does not have enough permissions...");

            AppEntity app = appsRepository.findById(_appId.longValue()).orElseThrow(
                    () -> new RuntimeException("app id does not exists..."));

            response.setId(app.getId());
            response.setName(app.getName());
            response.setAppDescription(app.getDescription());
            response.setPackageName(app.getPackageName());
            response.setCreatedAt(app.getCreatedAt().toString());
            response.setUpdatedAt(app.getUpdatedAt().toString());
            response.setIconUrl(app.getIconUrl());
            response.setInstallsCount(app.getInstallsCount());
            response.setUninstallsCount(app.getUninstallsCount());
            response.setVersions(appVersionsService.getAll(app.getId(), _account));

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Getting App Exception: " + _e.getMessage());
        }

        return response;
    }


    public FetchAllBasicApplicationResponse fetchAll(AuthenticatedAccount _account) {

        FetchAllBasicApplicationResponse response =  new FetchAllBasicApplicationResponse();

        try {

            //check perms
            GetAppsResponse appsInfo = getAll(_account);
            List<FetchBasicApplicationResponse> basicAppInfoList = new ArrayList<>();
            for(GetApplicationResponse appResponse : appsInfo.getAppsResponse()) {

                int filesCount = 0;
                String latestVersion = "";
                if(appResponse.getVersions().getVersions().size() <= 0)
                    latestVersion = "NA";
                else {

                    for(AppVersionEntity version : appVersionsRepository.findAll())
                        latestVersion = version.getVersionName();
                }

                for(AppVersionResponse versionRes : appResponse.getVersions().getVersions())
                    filesCount += versionRes.getFiles().size();

                FetchBasicApplicationResponse basicAppRes = new FetchBasicApplicationResponse();
                basicAppRes.setId(appResponse.getId());
                basicAppRes.setFilesCount(filesCount);
                basicAppRes.setCreatedAt(appResponse.getCreatedAt());
                basicAppRes.setName(appResponse.getName());
                basicAppRes.setDescription(appResponse.getAppDescription());
                basicAppRes.setIconUrl(appResponse.getIconUrl());
                basicAppRes.setPackageName(appResponse.getPackageName());
                basicAppRes.setVersionName(latestVersion);
                basicAppRes.setCreatedAt(appResponse.getCreatedAt());
                basicAppRes.setInstallsCount(appResponse.getInstallsCount());
                basicAppRes.setUninstallsCount(appResponse.getUninstallsCount());
                basicAppInfoList.add(basicAppRes);
            }

            response.setTimestamp(LocalDateTime.now());
            response.setApps(basicAppInfoList);
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) { response.setMessage("AppsService->fetchAll: " + _e.getMessage()); }

        return response;
    }

    public GetPaginatedAppsResponse getPaged(int _page, int _count, AuthenticatedAccount _account) {

        GetPaginatedAppsResponse response = new GetPaginatedAppsResponse();

        try {

            //check perms
            int pageNumber = Math.max(_page - 1, 0);
            List<FetchBasicApplicationResponse> pagedApps = new ArrayList<>();
            List<AppEntity> dbApps = appsRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNumber, _count));

            for(AppEntity app : dbApps) {

                FetchBasicApplicationResponse appResponse = new FetchBasicApplicationResponse();
                appResponse.setId(app.getId());
                appResponse.setName(app.getName());
                appResponse.setVersionName("latest");
                appResponse.setIconUrl(app.getIconUrl());
                appResponse.setPackageName(app.getPackageName());
                appResponse.setDescription(app.getDescription());
                appResponse.setInstallsCount(app.getInstallsCount());
                appResponse.setCreatedAt(app.getCreatedAt().toString());
                appResponse.setUninstallsCount(app.getUninstallsCount());

                int filesCount = 0;
                String latestVersionName = "NA";
                AppVersionEntity latestVersion = appVersionsService.findLastest(app);

                if(latestVersion != null) {

                    latestVersionName = Objects.requireNonNullElse(latestVersion.getVersionName(), "NA");
                    List<AppFileEntity> dbFiles = appFilesRepository.findAllByAppVersion(latestVersion);
                    filesCount = dbFiles.size();
                }

                appResponse.setVersionName(latestVersionName);
                appResponse.setFilesCount(filesCount);
                pagedApps.add(appResponse);
            }

            response.setPageId(_page);
            response.setApps(pagedApps);
            response.setTotalCount(appsRepository.count());

            response.setMessage("OK");
            response.setStatus(OperationStatus.SUCCESS);
        }
        catch (Exception _e) {

            System.out.println("========== getPaged ERROR ==========");
            System.out.println("Exception: " + _e);
            System.out.println("Class: " + _e.getClass().getName());
            _e.printStackTrace();
            System.out.println("====================================");

            response.setMessage("AppsService->getPaged: " + _e.getMessage());
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
            List<GetApplicationResponse> appResponses = new ArrayList<>();

            for(AppEntity app : apps)
                appResponses.add(getApp(app.getId(), _account));

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
