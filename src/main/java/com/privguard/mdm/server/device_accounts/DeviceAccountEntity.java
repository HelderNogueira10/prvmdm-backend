package com.privguard.mdm.server.device_accounts;

import java.time.LocalDateTime;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "device_accounts")
public class DeviceAccountEntity extends BaseEntity {
    
    @Column(nullable = false) private String imei;
    @Column(nullable = false) private String secret;
	@Column(nullable = false) private String hostname;
    @Column(nullable = false) LocalDateTime lastSeen;
    @OneToOne @JoinColumn(name = "account_id") private AccountEntity account;

    public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public LocalDateTime getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}
	public AccountEntity getAccount() {
		return account;
	}
	public void setAccount(AccountEntity account) {
		this.account = account;
	}

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
