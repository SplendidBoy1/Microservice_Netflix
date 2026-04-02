package com.example.user_service.config;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    private String SECRET = "mysecretkeymysecretkeymysecretkey123";

    private Key getKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public Claims validateToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }


}
