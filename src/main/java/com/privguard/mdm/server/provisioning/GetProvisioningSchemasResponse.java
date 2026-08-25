package com.privguard.mdm.server.provisioning;

import com.privguard.mdm.server.operations.OperationResponse;

import java.util.List;

public class GetProvisioningSchemasResponse extends OperationResponse  {

    private Long count;
    private List<GetProvisioningSchemaResponse> schemas;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<GetProvisioningSchemaResponse> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<GetProvisioningSchemaResponse> schemas) {
        this.schemas = schemas;
    }
}
