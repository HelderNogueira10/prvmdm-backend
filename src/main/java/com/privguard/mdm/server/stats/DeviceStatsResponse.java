package com.privguard.mdm.server.stats;

import com.privguard.mdm.server.operations.OperationResponse;

public class DeviceStatsResponse extends OperationResponse {

    private int alertsCount;
    private int onlineDevices;
    private int offlineDevices;
    private int pendingCommands;

    public int getAlertsCount() {
        return alertsCount;
    }

    public void setAlertsCount(int alertsCount) {
        this.alertsCount = alertsCount;
    }

    public int getOnlineDevices() {
        return onlineDevices;
    }

    public void setOnlineDevices(int onlineDevices) {
        this.onlineDevices = onlineDevices;
    }

    public int getOfflineDevices() {
        return offlineDevices;
    }

    public void setOfflineDevices(int offlineDevices) {
        this.offlineDevices = offlineDevices;
    }

    public int getPendingCommands() {
        return pendingCommands;
    }

    public void setPendingCommands(int pendingCommands) {
        this.pendingCommands = pendingCommands;
    }
}
