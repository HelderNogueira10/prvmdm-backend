package com.privguard.mdm.server.exceptions;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ExceptionsManager {
    
    private final ExceptionService exceptionsService;
    private static ExceptionsManager INSTANCE;

    public ExceptionsManager(ExceptionService _exceptionsService) {

        if(INSTANCE == null)
            INSTANCE = this;

        this.exceptionsService = _exceptionsService;
    }

    public String onErrorException(String _summury, String _stacktrace) {
        
        ExceptionRequest exception = new ExceptionRequest();
        exception.setStacktrace(_stacktrace);
        exception.setSummury(_summury);
        exception.setStatus(ExceptionTypes.ERROR);
        exception.setTimestamp(LocalDateTime.now());
        
        return getExceptionMessage(exception);
    }

    public static ExceptionsManager getInstance() { return INSTANCE; }
    
    private String getExceptionMessage(ExceptionRequest _request) { return _request.getSummury() + ": " + _request.getStacktrace(); }
}
