package com.privguard.mdm.server.exceptions;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {
    
    private ExceptionRepository exceptionRepository;

    public ExceptionService(ExceptionRepository _repo) {

        this.exceptionRepository = _repo;
    }

    public ExceptionResponse addException(ExceptionRequest _request) {

        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setSummury(_request.getSummury());
        exceptionEntity.setStacktrace(_request.getStacktrace());
        exceptionEntity.setTimestamp(_request.getTimestamp());
        exceptionEntity.setType(_request.getType());
        exceptionRepository.save(exceptionEntity);

        return new ExceptionResponse(_request.getStacktrace());
    }

}
