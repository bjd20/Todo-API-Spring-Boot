package com.example.todo_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

//  Intercepts every HTTP request.
//  Extracts JWT from Authorization: Bearer <token> header.
//  Validates token and sets user in Spring Security context.

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
            String token = extractToken(request);

            if(token != null && jwtUtil.validateToken(token)){
                String username = jwtUtil.extractUsername(token);

                // Create authentication token
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, null);

                // Set in Spring Security context
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // Invalid token, continue without authentication
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }

        return null;
    }
}
