package com.privguard.mdm.server.base;


import com.privguard.mdm.server.exceptions.ExceptionService;
import com.privguard.mdm.server.exceptions.ExceptionsManager;

public class BaseService {
    
    protected final ExceptionService exceptionsService;
    protected final ExceptionsManager exceptionsManager;

    public BaseService(ExceptionService _srv, ExceptionsManager _mgr) {

        this.exceptionsService = _srv;
        this.exceptionsManager = _mgr;
    }
}
