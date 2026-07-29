package com.privguard.mdm.server.heartbeat;

import com.privguard.mdm.server.base.BaseEntity;

import com.privguard.mdm.server.device_accounts.DeviceAccountEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "heartbeats")
public class HeartbeatEntity extends BaseEntity  {
    
    private Long uptime;
    private Integer batteryLevel;
	@OneToOne @JoinColumn(name = "device_account_id") private DeviceAccountEntity deviceAccount;

	public Long getUptime() { return uptime; }
	public Integer getBatteryLevel() { return batteryLevel; }
	public DeviceAccountEntity getDeviceAccount() { return deviceAccount; }

	public void setUptime(Long uptime) { this.uptime = uptime; }
	public void setBatteryLevel(Integer batteryLevel) { this.batteryLevel = batteryLevel; }
	public void setDeviceAccount(DeviceAccountEntity _deviceAccount) { this.deviceAccount = _deviceAccount; }
}
