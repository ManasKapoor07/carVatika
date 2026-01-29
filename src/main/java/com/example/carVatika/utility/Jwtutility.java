package com.example.carVatika.utility;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class Jwtutility {

    // 🔐 MUST be static & >= 32 bytes
    private static final String SECRET = "my-super-secret-key-my-super-secret-key-123456";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    private final long tokenExpiry = 3600000; // 1 hour

    // ================= TOKEN GENERATION =================
    public String generateToken(String email, String role, Long userId) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiry))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ================= TOKEN PARSING =================
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmailFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    public String getRoleFromToken(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return extractClaims(token).get("userId", Long.class);
    }

    // ================= TOKEN VALIDATION =================
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            System.out.println("JWT INVALID: " + e.getMessage());
            return false;
        }
    }

    // ================= AUTHORITIES =================
    public List<SimpleGrantedAuthority> getAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public long getExpirationTime() {
        return tokenExpiry;
    }
}
