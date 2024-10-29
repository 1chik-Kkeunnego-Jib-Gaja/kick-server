package com.example.kick.global.security.jwt;

import com.example.kick.global.security.auth.AuthDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetails authDetails;

    public String generateAccessToken(String id) {
        return generateToken(id, "access", jwtProperties.getAccessExp());
    }

    private String generateToken(String id, String type, Long exp) {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
            .setSubject(id)
            .claim("type", type)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
            .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());
        return parseToken(bearer);
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetails.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.replace(jwtProperties.getPrefix(), "");
        }
        return null;
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid or expired token");
        }
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }
}
