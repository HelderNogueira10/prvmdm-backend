package com.privguard.mdm.server.api_errors;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class ApiErrorsService {

    private ApiErrorsRepository mRepo;

    public ApiErrorsService(ApiErrorsRepository _repo) {

        this.mRepo =_repo;
    }

    public boolean addError(ApiErrorRequest _request, Integer _resStatus) {

        try {
            
            ApiErrorEntity apiError = new ApiErrorEntity();
            apiError.setError(_request.getError());
            apiError.setMessages(_request.getMessages());
            apiError.setResponseStatus(_resStatus);
            apiError.setTimestamp(LocalDateTime.now());
            mRepo.save(apiError);
        }
        catch(Exception _e) { return false; }

        return true;
    }
}
