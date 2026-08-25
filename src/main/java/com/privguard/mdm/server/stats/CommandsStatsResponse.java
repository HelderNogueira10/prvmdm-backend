package com.privguard.mdm.server.stats;

import com.privguard.mdm.server.operations.OperationResponse;

public class CommandsStatsResponse extends OperationResponse {

    private Long commandsCount;
    private Long failedCommandsCount;
    private Long runningCommandsCount;
    private Long pendingCommandsCount;
    private Long successfullCommandsCount;

    public Long getCommandsCount() {
        return commandsCount;
    }

    public void setCommandsCount(Long commandsCount) {
        this.commandsCount = commandsCount;
    }

    public Long getFailedCommandsCount() {
        return failedCommandsCount;
    }

    public void setFailedCommandsCount(Long failedCommandsCount) {
        this.failedCommandsCount = failedCommandsCount;
    }

    public Long getRunningCommandsCount() {
        return runningCommandsCount;
    }

    public void setRunningCommandsCount(Long runningCommandsCount) {
        this.runningCommandsCount = runningCommandsCount;
    }

    public Long getPendingCommandsCount() {
        return pendingCommandsCount;
    }

    public void setPendingCommandsCount(Long pendingCommandsCount) {
        this.pendingCommandsCount = pendingCommandsCount;
    }

    public Long getSuccessfullCommandsCount() {
        return successfullCommandsCount;
    }

    public void setSuccessfullCommandsCount(Long successfullCommandsCount) {
        this.successfullCommandsCount = successfullCommandsCount;
    }
}
