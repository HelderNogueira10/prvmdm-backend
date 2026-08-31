package com.privguard.mdm.server.device_accounts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.privguard.mdm.server.account.AccountsRepository;
import com.privguard.mdm.server.utils.StringsGenerator;

@Service
public class DeviceAccountsService {
    
    private final PasswordEncoder passwordEncoder;
    private AccountsRepository accountsRepository;
    private DeviceAccountsRepository mRepository;

    public DeviceAccountsService(DeviceAccountsRepository _repo, AccountsRepository _accountRepository, PasswordEncoder passwordEncoder) {

        this.mRepository = _repo;
        this.passwordEncoder = passwordEncoder;
    }

    public DeviceAccountEntity getByImei(String _imei) {

        return mRepository.findByImei(_imei).orElse(null);
    }

    public OperationResponse validHostname(String _hostname, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();

        try {

            //TODO: check permissions
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage(mRepository.existsByHostname(_hostname) ? "0" : "1");
        }
        catch (Exception _e) {

            _e.printStackTrace();
            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("DeviceAccountsService->validHostname: " + _e.getMessage());
        }

        return response;
    }

    public GetHostnamesResponse getHostnames(AuthenticatedAccount _account) {

        GetHostnamesResponse response = new GetHostnamesResponse();

        try {

            //TODO: check permissions
            response.setIds(new ArrayList<>());
            response.setHostnames(new ArrayList<>());

            List<DeviceAccountEntity> dbDevices = mRepository.findAll();
            for(DeviceAccountEntity device : dbDevices) {

                response.getHostnames().add(device.getHostname());
                response.getIds().add(device.getAccount().getId());
            }

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch(Exception _e) {

            _e.printStackTrace();
            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("DeviceAccountsService->getHostnames: " + _e.getMessage());
        }

        return response;
    }

    public DeviceAccountResponse add(DeviceAccountRequest _request, AccountEntity _account) {

        DeviceAccountResponse response = new DeviceAccountResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            DeviceAccountEntity deviceAccount = new DeviceAccountEntity();
            deviceAccount.setAccount(_account);
            deviceAccount.setImei(_request.getImei());
            deviceAccount.setLastSeen(LocalDateTime.now());
            deviceAccount.setHostname(_request.getHostname());
            deviceAccount.setSecret(passwordEncoder.encode(_request.getSecret()));
            mRepository.save(deviceAccount);

            response.setImei(_request.getImei());
            response.setAccountUuid(_account.getUuid());
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("device account created successfully.");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("adding device account exception: " + _e.getMessage());
        }

        return response;
    }
}
