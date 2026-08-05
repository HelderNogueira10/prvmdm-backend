package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/apps")
public class AppsController {


    private final AppsService appsService;
    @Value("${app.apk-directory}")
    private String apkDirectory;

    public AppsController(AppsService appsService) {
        this.appsService = appsService;
    }

    @GetMapping("/delete/{_id}")
    public OperationResponse delete(@PathVariable Integer _id, Authentication _auth) {

        return appsService.delete(_id, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @PostMapping("/add")
    public OperationResponse add(@Valid @RequestBody AddAppRequest _request, Authentication _auth) {

        AuthenticatedAccount authedAccount = (AuthenticatedAccount) _auth.getPrincipal();
        return appsService.add(_request, authedAccount);
    }

    @GetMapping("/get/{_id}")
    public GetAppResponse getApp(@PathVariable Integer _id, Authentication _auth) {

        AuthenticatedAccount account = (AuthenticatedAccount) _auth.getPrincipal();
        return appsService.getApp(_id, account);
    }

    @GetMapping("/get/all")
    public GetAppsResponse getAllApps(Authentication _auth) {

        return appsService.getAll((AuthenticatedAccount) _auth.getPrincipal());
    }

    @GetMapping("/statistics")
    public AppStatisticsResponse getStatistics(Authentication _auth) {

        AuthenticatedAccount authedAccount = (AuthenticatedAccount) _auth.getPrincipal();
        return appsService.getStatistics(authedAccount);
    }

    @GetMapping("/files/get/{_package}/{version}/{filename}")
    public void download(@PathVariable String _package, @PathVariable String version, @PathVariable String filename, HttpServletResponse response) throws IOException {

        Path path = Paths.get(apkDirectory, _package, version, filename);

        response.setContentType("application/vnd.android.package-archive");
        response.setContentLengthLong(Files.size(path));

        try (InputStream in = Files.newInputStream(path);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[8192];
            int n;
            long total = 0;

            try {
                while ((n = in.read(buffer)) != -1) {
                    out.write(buffer, 0, n);
                    total += n;

                    System.out.println("Sent " + total);
                }

                out.flush();
                System.out.println("Finished sending");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed after " + total + " bytes");
            }
        }
    }
}