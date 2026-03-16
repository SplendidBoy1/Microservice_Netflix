package com.example.auth_service.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private String SECRET_KEY = "mysecretkeymysecretkeymysecretkey123";

    public Key getSignKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UserDetails userDetails){
        String jwt = Jwts.builder().setSubject(userDetails.getUsername()).claim("roles", userDetails.getAuthorities()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000)).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        return jwt;
    }
}
