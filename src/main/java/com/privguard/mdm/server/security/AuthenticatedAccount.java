package com.privguard.mdm.server.security;

import com.privguard.mdm.server.account.AccountTypes;

public class AuthenticatedAccount {

    private Long id;
    private String uuid;
    private AccountTypes type;

    public Long getId() { return id; }
    public String getUuid() { return uuid; }
    public AccountTypes getType() { return type; }

    public void setId(Long _id) { this.id = _id; }
    public void setUuid(String _uuid) { this.uuid = _uuid; }
    public void setType(AccountTypes _type) { this.type = _type; }
}
