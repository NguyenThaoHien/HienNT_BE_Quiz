package com.example.quizapp.service.impl;

import com.example.quizapp.entity.User;
import com.example.quizapp.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TokenServiceImp implements TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpirationInMs;

    @Override
    public String generateToken(User user, Set<String> roles) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        SecretKey key = getSigningKey();
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("username", user.getFullName())
                .claim("email", roles)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact()
                ;
    }

    @Override
    public Authentication getAuthenticationFromToken(String token) {
        if(token == null || token.isBlank()) return null;

        try {
            Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();

            String userId = claims.getSubject();

            List<String> roles = claims.get("roles", List.class);

            Set<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());


            org.springframework.security.core.userdetails.User principal = new org.springframework.security.core.userdetails.User(userId, "", authorities);

            return new UsernamePasswordAuthenticationToken(principal, token, authorities);
        } catch (ExpiredJwtException e) {
            return null;
        } catch (JwtException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
