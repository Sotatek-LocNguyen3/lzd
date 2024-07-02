package com.example.lazadu.security;

import com.example.lazadu.entity.Role;
import com.example.lazadu.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class JwtTokenUtil {

    private static final String USER_ID = "userId";
    private static final String ROLES = "roles";

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.signing-key}")
    private String signingKey;

    public boolean isTokenValid(final String token) {
        try {
            Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody()
                .get(USER_ID, String.class);
    }

    public String generateToken(@NonNull final User user,
                                @NonNull final Set<Role> roles) {
        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + expiration);
        final Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, user.getId());
        claims.put(ROLES, roles);
        return "Bearer "
                + Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }
}
