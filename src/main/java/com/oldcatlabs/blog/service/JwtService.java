package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.properties.SecretProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final SecretProperties secretProperties;

    public String generateToken(UserDetails userDetails) {
        var claims = new HashMap<String, String>();
        claims.put("iss", secretProperties.getJwtIss());
        Instant now = Instant.now();
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(10*60)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretProperties.getJwtSecretKey());
        return Keys.hmacShaKeyFor(decodedKey);
    }

}
