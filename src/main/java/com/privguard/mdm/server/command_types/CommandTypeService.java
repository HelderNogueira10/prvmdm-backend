package com.privguard.mdm.server.command_types;

import com.privguard.mdm.server.command.GetCommandResponse;
import com.privguard.mdm.server.operations.OperationResponse;
import com.privguard.mdm.server.operations.OperationStatus;
import com.privguard.mdm.server.security.AuthenticatedAccount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandTypeService {

    private CommandTypeRepository mRepository;

    public CommandTypeService(CommandTypeRepository _mRepo) {

        this.mRepository = _mRepo;
    }

    public GetCommandTypesResponse getTypes(AuthenticatedAccount _account) {

        GetCommandTypesResponse response = new GetCommandTypesResponse();

        try {

            //checlk perms //TODO: check permissions
            List<String> typesList = new ArrayList<>();
            for (CommandTypeEntity type : mRepository.findAll())
                typesList.add(type.getType());

            response.setTypes(typesList);
            response.setStatus(OperationStatus.SUCCESS);
            response.setMessage("OK");
        }
        catch (Exception _e) {

            response.setStatus(OperationStatus.FAILURE);
            response.setMessage("CommandTypeService->getTypes: " + _e.getMessage());
        }

        return response;
    }
}
