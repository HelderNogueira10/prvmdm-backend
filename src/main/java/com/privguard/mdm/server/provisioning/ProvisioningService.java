package com.privguard.mdm.server.provisioning;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.privguard.mdm.server.exceptions.ExceptionsManager;
import com.privguard.mdm.server.managers.ServicesManager;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.tokens.TokenEntity;
import com.privguard.mdm.server.tokens.TokenStatus;
import com.privguard.mdm.server.tokens.TokensRepository;
import com.privguard.mdm.server.utils.StringsGenerator;

@Service
public class ProvisioningService {
    
    private final ServicesManager servicesManager;
    private ProvisioningRepository mRepository;
    private TokensRepository tokensRepository;

    public ProvisioningService(ProvisioningRepository _mRepository, ServicesManager servicesManager, TokensRepository _tokensRepository) {

        this.mRepository = _mRepository;
        this.servicesManager = servicesManager;
        this.tokensRepository = _tokensRepository;
    }

    public ProvisioningResponse getEnrollment(String _name) {

        try {
            
            String enrollmentToken = StringsGenerator.generateRandomString(60);

            TokenEntity token = new TokenEntity();
            token.setExpireDate(LocalDateTime.now().plusMinutes(10));
            token.setStartDate(LocalDateTime.now());
            token.setStatus(TokenStatus.ACTIVE);
            token.setToken(enrollmentToken);
            tokensRepository.save(token);

            ProvisioningEntity entity = mRepository.findBySchemaName(_name);
            if(entity == null)
                return null;

            ProvisioningExtrasResponse extras = new ProvisioningExtrasResponse();
            extras.setTokenId(String.valueOf(token.getId()));
            extras.setEnrollmentToken(token.getToken());

            ProvisioningResponse response = new ProvisioningResponse();
            response.setExtras(extras);
            response.setComponentName(entity.getComponentName());
            response.setDownloadLocation(entity.getDownloadLocation());
            response.setSignatureChecksum(entity.getSignatureChecksum());
            response.setWifiSSID(entity.getWifiSSID());
            response.setWifiSecurity(entity.getWifiSecurity());
            response.setWifiPassword(entity.getWifiPassword());
            response.setWifiHidden(entity.isWifiHidden());
            response.setEncrypted(entity.isEncryptDevice());
            response.setLeaveAllSystemAppsEnabled(entity.isLeaveSystemApps());
            response.setTimezone(entity.getTimezone());
            response.setLocale(entity.getLocale());
            response.setOrganizationName(entity.getOrganizationName());

            return response;
        }
        catch(Exception _e) { throw new RuntimeException(ExceptionsManager.getInstance().onErrorException("Provisioning Enrollment Failed: ", _e.getMessage())); } 
    }

    public OperationResponse createSchema(ProvisioningRequest _request) {

        OperationResponse response = new OperationResponse();

        try {

            ProvisioningEntity entity = new ProvisioningEntity();
            entity.setSchemaName(_request.getSchemaName());
            entity.setComponentName(_request.getComponentName());
            entity.setDownloadLocation(_request.getDownloadLocation());
            entity.setSignatureChecksum(_request.getSignatureChecksum());
            entity.setWifiSSID(_request.getWifiSSID());
            entity.setWifiPassword(_request.getWifiPassword());
            entity.setWifiSecurity(_request.getWifiSecurity());
            entity.setWifiHidden(_request.isWifiHidden());
            entity.setTimezone(_request.getTimezone());
            entity.setLocale(_request.getLocale());
            entity.setEncryptDevice(_request.isEncryptDevice());
            entity.setLeaveSystemApps(!_request.isDebloatDevice());
            entity.setOrganizationName(_request.getOrganizationName());
            mRepository.save(entity);
    
            response.setStatus(OperationStatus.SUCCESS);;
            response.setMessage("OK");
        }
        catch(Exception _e) { 

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Enrollment Schema Error: " + _e.getMessage());
        } 
        return response;
    }

    public OperationResponse deleteSchema(String _name) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            ProvisioningEntity entity = mRepository.findBySchemaName(_name);
            mRepository.delete(entity);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Provisioning Schema " + _name + " deleted successfully!");
        }
        catch(Exception _e) {

            response.setMessage("Enrollment Schema Error: " + _e.getMessage());
        }

        return response;
    }
} 
