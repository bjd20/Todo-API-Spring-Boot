package com.example.todo_api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

//****************************- Act as a “JWT helper toolkit” -**********************************

@Component
public class JwtUtil {

//  Configuration fields (injected from application.properties)

//    if jwt.secret is missing, spring uses the default value i.e after the ':'(colon).
    @Value("${jwt.secret:my_super_secret_key_at_least_32_chars_long!!}")
    private String secret;

    @Value("${jwt.expiration:86400000}")        // 24 hours in ms
    private long expiration;

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());
    }

//  Creates JWT with username inside.
    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration ))
                .signWith(getSigningKey())
                .compact();
    }

//  Reads username from token.
    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

//  Verifies token is legit.
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return  true;
        } catch (Exception e) {
            return false;
        }
    }
}
