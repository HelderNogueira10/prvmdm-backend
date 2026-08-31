package com.privguard.mdm.server.command;

import java.time.LocalDateTime;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.command_types.CommandTypeEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="commands")
public class CommandEntity extends BaseEntity {

    private String payload;
    private LocalDateTime endedAt;
    private LocalDateTime startedAt;
    @NotNull @Enumerated(EnumType.STRING) private CommandStatus status;
    @NotNull @ManyToOne @JoinColumn(name = "command_type_id") private CommandTypeEntity type;
    @NotNull @ManyToOne @JoinColumn(name="target_id") private DeviceAccountEntity deviceId;
    @NotNull @ManyToOne @JoinColumn(name="requester_id") private AccountEntity accountId;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public CommandTypeEntity getType() {
        return type;
    }

    public void setType(CommandTypeEntity type) {
        this.type = type;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public void setStatus(CommandStatus status) {
        this.status = status;
    }

    public DeviceAccountEntity getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceAccountEntity deviceId) {
        this.deviceId = deviceId;
    }

    public AccountEntity getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountEntity accountId) {
        this.accountId = accountId;
    }
}
