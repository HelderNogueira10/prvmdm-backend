package com.privguard.mdm.server.security;

import com.privguard.mdm.server.account.AccountEntity;
import com.privguard.mdm.server.account.AccountTypes;
import com.privguard.mdm.server.account_tokens.TokenGenerationRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getClaims(String _jwt) {

        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(_jwt)
                    .getPayload();
        }
        catch(Exception _e) { throw new RuntimeException("invalid jwt token"); }
    }

    public String getJti(String _jwt) {

        return getClaims(_jwt).getId();
    }

    public String getAccountUuid(String _jwt) {

        return getClaims(_jwt).getSubject();
    }

    public AccountTypes getAccountType(String _jwt) {

        String type = getClaims(_jwt).get("type", String.class);
        return AccountTypes.valueOf(type);
    }

    public boolean isExpired(String _jwt) {

        return getClaims(_jwt)
                .getExpiration().before(new java.util.Date());
    }

    public String generateToken(TokenGenerationRequest _request) {

        return Jwts.builder()
                .subject(_request.getAccountUuid())
                .claim("type", _request.getAccountType().name())
                .id(_request.getJti())
                .issuedAt(Date.from(_request.getIssuedAt()))
                .expiration(Date.from(_request.getExpireAt()))
                .signWith(getSigningKey())
                .compact();
    }
}
