package com.example.user_service.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.user_service.dto.CustomUserDetails;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        // System.out.println("QUANASDFNASDF");
        // System.out.println(token);

        try {
            Claims claims = jwtUtil.validateToken(token);
            System.out.println(claims.toString());
            System.out.println(claims.get("roles"));

            String username = claims.getSubject();

            Long userId = claims.get("userId", Long.class);

            List<Map<String, String>> roleMaps = claims.get("roles", List.class);

            List<String> roles = roleMaps.stream().map(m -> m.get("authority")).collect(Collectors.toList());

            List<GrantedAuthority> authorities = (List<GrantedAuthority>)(List<?>)roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

            CustomUserDetails userDetails = new CustomUserDetails(userId, username, authorities);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

}
