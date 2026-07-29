package com.privguard.mdm.server.servers_info;

import com.privguard.mdm.server.account.*;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import com.privguard.mdm.server.server_health.ServerHealthStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServersInfoService {

    private final ServersInfoRepository serversInfoRepository;
    private final AccountsService accountsService;
    private final AccountsRepository accountsRepository;

    public ServersInfoService(ServersInfoRepository serversInfoRepository, AccountsService accountsService, AccountsRepository accountsRepository) {
        this.serversInfoRepository = serversInfoRepository;
        this.accountsService = accountsService;
        this.accountsRepository = accountsRepository;
    }

    public OperationResponse updateServer(ServerInfoRequest _serverInfo, AuthenticatedAccount _authedAccount) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            AccountEntity authedAccount = accountsService.getAccount(_authedAccount.getUuid());
            if(authedAccount == null)
                throw new RuntimeException("Header Authed Account Does Not Exist ...");

            if (authedAccount.getType() != AccountTypes.USER_ACCOUNT)
                throw new RuntimeException("insuficient permissions ...");

            //check perms later
            ServerInfoEntity serverInfo = serversInfoRepository.findByServerName(_serverInfo.getServerName())
                    .orElse(new ServerInfoEntity());

            serverInfo.setServerLoad(Long.valueOf(10));
            serverInfo.setServerIP(_serverInfo.getServerIP());
            serverInfo.setServerName(_serverInfo.getServerName());
            serverInfo.setServerPort(_serverInfo.getServerPort());
            serverInfo.setServerStatus(_serverInfo.getServerStatus());
            serversInfoRepository.save(serverInfo);

            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("New Server Info Has Been Added: " + serverInfo.getServerIP() + ":" + serverInfo.getServerPort().toString());
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Adding New Server Info Exception: " + _e.getMessage());
        }

        return response;
    }

    public OperationResponse deleteServer(Long _serverId, AuthenticatedAccount _account) {

        OperationResponse response = new OperationResponse();
        response.setStatus(OperationStatus.FAILURE);

        try {

            AccountEntity authedAccount = accountsService.getAccount(_account.getUuid());
            if(authedAccount == null)
                throw new RuntimeException("Header Authed Account Does Not Exist ...");

            if (authedAccount.getType() != AccountTypes.USER_ACCOUNT)
                throw new RuntimeException("insuficient permissions ...");

            ServerInfoEntity serverInfo = serversInfoRepository.findById(_serverId)
                    .orElseThrow(() -> new RuntimeException("server name not found"));

            serversInfoRepository.delete(serverInfo);
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("Server Info => " + serverInfo.getServerName() + " has been deleted!");
        }
        catch(Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Accounts Service: ");
        }

        return response;
    }

    public List<ServerInfoResponse> getAvailableServers() {

        List<ServerInfoResponse> serversResponse = new ArrayList<>();

        try {

            List<ServerInfoEntity> availableServers = serversInfoRepository.findAllByServerStatus(ServerHealthStatus.OPTIMAL);
            for(ServerInfoEntity server : availableServers) {

                ServerInfoResponse response = new ServerInfoResponse();
                response.setServerId(server.getId());
                response.setServerName(server.getServerName());
                response.setServerIP(server.getServerIP());
                response.setServerPort(server.getServerPort());
                response.setServerStatus(server.getServerStatus());
                serversResponse.add(response);
            }
        }
        catch(Exception _e) {

            System.out.println("GetAvailableServers Exception: " + _e.getMessage());
        }

        return serversResponse;
    }
}
