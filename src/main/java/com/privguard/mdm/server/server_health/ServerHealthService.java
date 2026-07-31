package com.privguard.mdm.server.server_health;

import com.privguard.mdm.server.servers_info.ServerInfoEntity;
import com.privguard.mdm.server.servers_info.ServerInfoResponse;
import com.privguard.mdm.server.servers_info.ServersInfoService;
import org.springframework.stereotype.Service;

import com.privguard.mdm.server.operations.OperationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServerHealthService {

    private final ServersInfoService serversInfoService;
    private ServerHealthRepository mRepository;

    public ServerHealthService(ServerHealthRepository _repo, ServersInfoService serversInfoService) {

        this.mRepository = _repo;
        this.serversInfoService = serversInfoService;
    }

    public ServerHealthResponse getHealthStatus(ServerHealthStatus _status) {

        ServerHealthResponse response = new ServerHealthResponse();
        response.setStatus(OperationStatus.FAILURE);
        
        try {
            
            ServerHealthEntity healthStatus = new ServerHealthEntity();
            healthStatus.setHealthStatus(_status);
            mRepository.save(healthStatus);

            List<ServerInfoResponse> serversList = serversInfoService.getAvailableServers();
            //do ping tests to determine the best one

            response.setPreferedServerId(5);
            response.setTimestamp(LocalDateTime.now().toString());
            response.setAvailableServers(serversInfoService.getAvailableServers());

            response.setMessage("OK");
            response.setStatus(OperationStatus.SUCCESS);
        }
        catch(Exception _e) { 

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("Server Health Exception: " + _e.getMessage());
        }

        return response;
    }
}
