package com.trade.free.item.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtService {



    private final String jwtSigningKey;


    public JwtService(@Value("${token.signing.key}") String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }


    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Object extractUserId(String token) {
        return extractParams(token, "id");
    }

    public List<String> extractPermission(String token) {
        return (List<String>) extractParams(token, "permission");
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Object extractParams(String token, String key) {
        return extractAllClaims(token).get(key);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
