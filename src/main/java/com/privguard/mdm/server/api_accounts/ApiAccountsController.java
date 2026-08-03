package com.privguard.mdm.server.api_accounts;

import com.privguard.mdm.server.security.AuthenticatedAccount;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts/api")
public class ApiAccountsController {

    private final ApiAccountsService apiAccountsService;

    public ApiAccountsController(ApiAccountsService apiAccountsService) {
        this.apiAccountsService = apiAccountsService;
    }

    @PostMapping("/create")
    public AddApiAccountResponse createApiAccount(@Valid @RequestBody AddApiAccountRequest _request, Authentication _auth) {

        return apiAccountsService.create(_request, (AuthenticatedAccount) _auth.getPrincipal());
    }
}
