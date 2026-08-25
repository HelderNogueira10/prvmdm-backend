package com.privguard.mdm.server.app_files;

import com.beust.ah.A;
import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.app_versions.AppVersionEntity;
import com.privguard.mdm.server.app_versions.AppVersionsRepository;
import com.privguard.mdm.server.apps.AddAppRequest;
import com.privguard.mdm.server.apps.AppEntity;
import com.privguard.mdm.server.apps.AppsRepository;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.utils.HashingUtils;
import jdk.dynalink.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AppFilesService {

    private final AppVersionsRepository appVersionsRepository;
    private final AppsRepository appsRepository;
    private AppFilesRepository appFilesRepository;

    public AppFilesService(AppFilesRepository _appFilesRepository, AppVersionsRepository appVersionsRepository, AppsRepository appsRepository) {

        this.appFilesRepository = _appFilesRepository;
        this.appVersionsRepository = appVersionsRepository;
        this.appsRepository = appsRepository;
    }

    public List<AppFileEntity> fetchFiles(AppVersionEntity _appVersion, AuthenticatedAccount _account) {

        try {

            //check perms
            return appFilesRepository.findAllByAppVersion(_appVersion);
        }
        catch (Exception _e) { return null; }
    }

    public OperationResponse delete(Long _fileId, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();

        try {

            //check perms
            AppFileEntity dbFile = appFilesRepository.findById(_fileId).orElseThrow(() -> new RuntimeException(("not found")));

            if(Files.exists(Path.of(dbFile.getRealPath()))) {

                response.setMessage("file found ....");
                Files.delete(Path.of(dbFile.getRealPath()));
            }
            else response.setMessage("file not found...");

            appFilesRepository.delete(dbFile);
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setMessage("AppFilesService->delete: " + _e.getMessage());
        }

        return response;
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

    public OperationResponse upload(Integer _versionId, MultipartFile _file, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();

        try {

            //xheck perms
            AppVersionEntity dbVersion = appVersionsRepository.findById(_versionId.longValue()).orElseThrow(
                    () -> new RuntimeException("app version not found"));

            Path uploadDir = Paths.get(
                    "apks", dbVersion.getApp().getPackageName(), dbVersion.getVersionName());

            Files.createDirectories(uploadDir);
            Path dest = uploadDir.resolve(_file.getOriginalFilename());
            _file.transferTo(dest);

            AppFileEntity dbFile = new AppFileEntity();
            dbFile.setAppVersion(dbVersion);
            dbFile.setRealPath(dest.toString());
            dbFile.setFilename(_file.getOriginalFilename());
            dbFile.setFileLength(_file.getSize());
            dbFile.setChecksum(HashingUtils.sha256(dest));
            appFilesRepository.save(dbFile);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) {

            response.setMessage("AppFilesService->upload: " + _e.getMessage());
        }

        return response;
    }

}
