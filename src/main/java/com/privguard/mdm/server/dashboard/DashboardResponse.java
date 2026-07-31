package com.privguard.mdm.server.dashboard;

import com.privguard.mdm.server.operations.OperationResponse;
import io.swagger.v3.oas.models.Operation;

public class DashboardResponse extends OperationResponse {

    private int applications;
    private int onlineDevices;
    private int offlineDevices;
    private int pendingCommands;

    public int getApplications() { return applications; }
    public int getOnlineDevices() { return onlineDevices; }
    public int getOfflineDevices() { return offlineDevices; }
    public int getPendingCommands() { return pendingCommands; }

    public void setApplications(int _applications) { this.applications = _applications; }
    public void setOnlineDevices(int _onlineDevices) { this.onlineDevices = _onlineDevices; }
    public void setOfflineDevices(int _offlineDevices) { this.offlineDevices = _offlineDevices; }
    public void setPendingCommands(int _pendingCommands) { this.pendingCommands = _pendingCommands; }
}
