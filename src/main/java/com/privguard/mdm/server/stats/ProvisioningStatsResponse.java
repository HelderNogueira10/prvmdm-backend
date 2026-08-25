package com.privguard.mdm.server.stats;

import com.privguard.mdm.server.operations.OperationResponse;

public class ProvisioningStatsResponse extends OperationResponse {

    private String lastProvisioned;

    private int totalProvisioned;
    private int totalActive;
    private int totalPending;
    private int totalFailed;
    private int totalRevoked;
    private int totalOffline;

    public String getLastProvisioned() {
        return lastProvisioned;
    }

    public void setLastProvisioned(String lastProvisioned) {
        this.lastProvisioned = lastProvisioned;
    }

    public int getTotalProvisioned() {
        return totalProvisioned;
    }

    public void setTotalProvisioned(int totalProvisioned) {
        this.totalProvisioned = totalProvisioned;
    }

    public int getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(int totalActive) {
        this.totalActive = totalActive;
    }

    public int getTotalPending() {
        return totalPending;
    }

    public void setTotalPending(int totalPending) {
        this.totalPending = totalPending;
    }

    public int getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(int totalFailed) {
        this.totalFailed = totalFailed;
    }

    public int getTotalRevoked() {
        return totalRevoked;
    }

    public void setTotalRevoked(int totalRevoked) {
        this.totalRevoked = totalRevoked;
    }

    public int getTotalOffline() {
        return totalOffline;
    }

    public void setTotalOffline(int totalOffline) {
        this.totalOffline = totalOffline;
    }
}
