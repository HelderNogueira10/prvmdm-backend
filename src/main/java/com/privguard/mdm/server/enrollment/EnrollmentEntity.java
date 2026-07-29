package com.privguard.mdm.server.enrollment;

import java.time.LocalDateTime;

import com.privguard.mdm.server.base.BaseEntity;
import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="enrollments")
public class EnrollmentEntity extends BaseEntity {
    
    private LocalDateTime endEnrollDate;
    @NotNull private LocalDateTime startEnrollDate;
    @NotNull @Enumerated(EnumType.STRING) private EnrollmentStatus status;
    @NotNull @OneToOne @JoinColumn(name="device_id") private DeviceAccountEntity device;

    public LocalDateTime getStartEnrollDate() {
        return startEnrollDate;
    }

    public void setStartEnrollDate(LocalDateTime startEnrollDate) {
        this.startEnrollDate = startEnrollDate;
    }

    public LocalDateTime getEndEnrollDate() {
        return endEnrollDate;
    }

    public void setEndEnrollDate(LocalDateTime endEnrollDate) {
        this.endEnrollDate = endEnrollDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public DeviceAccountEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceAccountEntity deviceUuid) {
        this.device = deviceUuid;
    }
}
