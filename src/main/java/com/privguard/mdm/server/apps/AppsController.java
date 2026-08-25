package com.privguard.mdm.server.apps;

import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
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

    @PostMapping("/edit")
    public OperationResponse editApp(@Valid @RequestBody EditAppRequest _req, Authentication _auth) {

        return appsService.editApp(_req, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @DeleteMapping("/delete")
    public OperationResponse delete(@RequestParam Integer id, Authentication _auth) {

        return appsService.delete(id, (AuthenticatedAccount) _auth.getPrincipal());
    }

    @PostMapping("/add")
    public OperationResponse add(@Valid @RequestBody AddAppRequest _request, Authentication _auth) {

        AuthenticatedAccount authedAccount = (AuthenticatedAccount) _auth.getPrincipal();
        return appsService.add(_request, authedAccount);
    }

    @GetMapping("/get")
    public GetApplicationResponse getApp(@RequestParam Integer id, Authentication _auth) {

        AuthenticatedAccount account = (AuthenticatedAccount) _auth.getPrincipal();
        return appsService.getApp(id.longValue(), account);
    }

    @GetMapping("/fetch/all")
    public FetchAllBasicApplicationResponse fetchAllAppsBasic(Authentication _auth) {

        return appsService.fetchAll((AuthenticatedAccount) _auth.getPrincipal());
    }

    @GetMapping("/paged")
    public GetPaginatedAppsResponse getPaged(@RequestParam Integer pageId, @RequestParam Integer count, Authentication _auth) {

        return appsService.getPaged(pageId, count, (AuthenticatedAccount) _auth.getPrincipal());
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