package com.communicare.CommuniCareBackend.Application.config;
//Mobile App

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtilApp {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate a JWT token with claims
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract the username (email) from the JWT token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validate the JWT token
    public boolean validateToken(String token, String userEmail) {
        final String username = extractUsername(token);
        return (username.equals(userEmail) && !isTokenExpired(token));
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Retrieve claims from the JWT token
    public Claims getClaims(String token) {
        JwtParser parser = (JwtParser) Jwts.parser()  // Use the older Jwts.parser() method
                .setSigningKey(SECRET_KEY);  // Set the signing key for validation
        return parser.parseClaimsJws(token)  // Parse the JWT token
                .getBody();  // Extract and return the claims from the token
    }
}
