package com.privguard.mdm.server.security;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Encoders;

import javax.crypto.SecretKey;

public class GenerateJwtSecret {

    public static void main(String[] args) {

        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        System.out.println(Encoders.BASE64.encode(key.getEncoded()));
    }
}
