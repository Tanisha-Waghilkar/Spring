package com.sprk.employee_management.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {
    private final String secret="my-secret-jwt-key-which-is-the-secret-key ";
    public String generateToken(String username){
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()),SignatureAlgorithm.HS256).compact();
    }
    //symmetric key using here
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secret.getBytes())
                .build().parseClaimsJws(token).getBody().getSubject();
    }
}