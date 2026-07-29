package com.privguard.mdm.server.operations;

import org.springframework.stereotype.Service;

@Service
public class OperationsService {
    
    private OperationsRepository mRepository;

    public OperationsService(OperationsRepository _mRepository) {

        this.mRepository = _mRepository;
    }

    public OperationResponse createSchema(OperationRequest _request) {

        OperationResponse response = new OperationResponse();
        
        try {
        
            OperationEntity operation = new OperationEntity();
            operation.setMessage(_request.getMessage());
            operation.setStatus(_request.getStatus());
            mRepository.save(operation);

            response.setMessage("Operation Completed Successfully: " + _request.getMessage());
            response.setStatus(OperationStatus.SUCCESS);
        }
        catch(Exception _e) { 

            response.setMessage("Operation Failed: " + _request.getMessage());
            response.setStatus(OperationStatus.FAILURE);
        }

        return response;
    }
}
