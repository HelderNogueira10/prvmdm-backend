package com.privguard.mdm.server.enrollment;

import com.privguard.mdm.server.operations.OperationResponse;

public class EnrollmentResponse extends OperationResponse {
    
    private String agentUuid;
    private String agentSecret;

    public String getAgentUuid() { return agentUuid; }
    public String getAgentSecret() { return agentSecret; }

    public void setAgentUuid(String _agentUuid) { this.agentUuid = _agentUuid; }
    public void setAgentSecret(String _agentSecret) { this.agentSecret = _agentSecret; }
}
