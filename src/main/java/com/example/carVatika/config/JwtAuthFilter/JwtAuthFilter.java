package com.example.carVatika.config.JwtAuthFilter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.carVatika.utility.Jwtutility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final Jwtutility jwtutility;

    public JwtAuthFilter(Jwtutility jwtutility) {
        this.jwtutility = jwtutility;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // 🔹 Skip CORS preflight
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization header: " + authHeader);

        // 🔹 No token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔹 Already authenticated
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // 🔹 Invalid token
        if (!jwtutility.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔹 Extract claims
        Long userId = jwtutility.getUserIdFromToken(token);
        String role = jwtutility.getRoleFromToken(token);

        System.out.println("JWT VALID, userId=" + userId + ", role=" + role);

        // 🔹 Build authentication
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userId,
                null,
                jwtutility.getAuthorities(role));

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        // 🔹 Set context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
