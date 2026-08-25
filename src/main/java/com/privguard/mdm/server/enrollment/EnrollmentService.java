package com.privguard.mdm.server.enrollment;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.privguard.mdm.server.device_accounts.DeviceAccountResponse;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.stereotype.Service;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.account.AccountsRepository;
import com.privguard.mdm.server.account.AccountsService;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountRequest;
import com.privguard.mdm.server.device_accounts.DeviceAccountsService;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.tokens.TokenEntity;
import com.privguard.mdm.server.tokens.TokenStatus;
import com.privguard.mdm.server.tokens.TokensRepository;
import com.privguard.mdm.server.utils.StringsGenerator;

@Service
public class EnrollmentService {

    private final AccountsRepository accountsRepository;
    private TokensRepository tokensRepository;
    private EnrollmentRepository mRepository;

    private DeviceAccountsService deviceAccountsService;
    private AccountsService accountsService;

    public EnrollmentService(
        EnrollmentRepository _repository, 
        TokensRepository _tokensRepo,
        DeviceAccountsService _agentsService,
        AccountsRepository accountsRepository,
        AccountsService _accountsService,
        DeviceAccountsService _deviceAccountsService
    ) {

        this.mRepository = _repository;
        this.tokensRepository = _tokensRepo;
        this.deviceAccountsService = _deviceAccountsService;
        this.accountsRepository = accountsRepository;
        
        this.accountsService = _accountsService;
        this.deviceAccountsService = _deviceAccountsService;
    }

    public GetEnrollmentsGLPIResponse getAllEnrollments(AuthenticatedAccount _account) {

        GetEnrollmentsGLPIResponse response = new GetEnrollmentsGLPIResponse();

        try {

            //chekc perms
            List<EnrollmentEntity> dbEnrollments = mRepository.findAll();
            List<GetEnrollmentGLPIResponse> enrollmentsList = new ArrayList<>();

            for(EnrollmentEntity dbEnrollment : dbEnrollments) {

                String startDate = dbEnrollment.getStartEnrollDate() == null ? "NA" : dbEnrollment.getStartEnrollDate().toString();
                String endDate = dbEnrollment.getEndEnrollDate() == null ? "NA" : dbEnrollment.getEndEnrollDate().toString();

                GetEnrollmentGLPIResponse enrollmentResponse = new GetEnrollmentGLPIResponse();
                enrollmentResponse.setEnrollmentStatus(dbEnrollment.getStatus().toString());
                enrollmentResponse.setHostname(dbEnrollment.getDevice().getAccount().getUuid());
                enrollmentResponse.setFinishedAt(endDate);
                enrollmentResponse.setStartedAt(startDate);
                enrollmentResponse.setStatus(dbEnrollment.getDevice().getAccount().getStatus().toString());
                enrollmentResponse.setSchemaUsed("NA"); //TODO: enrollments must have schema used field
                enrollmentResponse.setTokenId(Long.valueOf(0)); //TODO: enrollments must keep track of token used to enroll
                enrollmentsList.add(enrollmentResponse);
            }

            response.setEnrollments(enrollmentsList);
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("EnrollmentService->getAllEnrollments: " + _e.getMessage());
            _e.printStackTrace();
        }

        return response;
    }

    public OperationResponse confirmEnrollment(EnrollmentRequest _request) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            //Update Enrollment Process
            EnrollmentEntity enrollment = mRepository.findByDeviceId(_request.getDeviceId());
            enrollment.setEndEnrollDate(LocalDateTime.now());
            enrollment.setStatus(_request.getStatus());
            mRepository.save(enrollment);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Enrollment Has Been Confirmed Successfully!");
        }
        catch(Exception _e) { 

            response.setMessage("Enrollment Confirmation Exception: " + _e.getMessage());
        }

        return response;
    }

    public EnrollmentResponse validateEnrollmentToken(TokenEnrollmentRequest _request) {

        EnrollmentResponse response = new EnrollmentResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            String agentSecret = StringsGenerator.generateRandomString(60);
            TokenEntity dbToken = tokensRepository.findById(_request.getId()).orElseThrow();
            
            if(dbToken == null)
                throw new Exception("token not found");

            if(!dbToken.getStatus().equals(TokenStatus.ACTIVE))
                throw new Exception("Invalid token status");

            if(!dbToken.getToken().equals(_request.getEnrollmentToken()))
                throw new Exception("invalid token!");

            if(dbToken.getExpireDate().isBefore(LocalDateTime.now()))
                throw new Exception("token has expired");

            dbToken.setStatus(TokenStatus.REVOKED);
            tokensRepository.save(dbToken);

            DeviceAccountRequest deviceRequest = new DeviceAccountRequest();
            deviceRequest.setImei(_request.getEmei());
            deviceRequest.setSecret(agentSecret);

            DeviceAccountEntity deviceAccount = deviceAccountsService.getByImei(accountsService.addDeviceAccount(deviceRequest).getImei());

            EnrollmentEntity enrollment = new EnrollmentEntity();
            enrollment.setStartEnrollDate(LocalDateTime.now());
            enrollment.setStatus(EnrollmentStatus.PENDING);
            enrollment.setDevice(deviceAccount);
            mRepository.save(enrollment);

            response.setAgentSecret(agentSecret);
            response.setAgentUuid(deviceAccount.getAccount().getUuid());
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Enrollment Token Has Benn Validated!");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Enrollment Token Validation Exception: " + _e.getMessage());
        }

        return response;
    }
}
