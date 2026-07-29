package com.privguard.mdm.server.tokens;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<TokenEntity, Long>{ }
