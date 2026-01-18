package com.oldcatlabs.blog.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;

@Service
public class JwtService {

    public String generateToken(UserDetails userDetails) {
        var claims = new HashMap<String, String>();
        claims.put("iss", "https://oldcatlabs.com");
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
        byte[] decodedKey = Base64.getDecoder().decode("y7t0b7xR9z+0n4Z8y3r7Zx+ZpQk3G6Z9y6qVvPpC5mE=");
        return Keys.hmacShaKeyFor(decodedKey);
    }

}
