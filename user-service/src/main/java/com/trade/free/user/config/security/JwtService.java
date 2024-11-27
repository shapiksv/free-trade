package com.trade.free.user.config.security;

import com.trade.free.user.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.trade.free.dto.permission.Permission.*;

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

    private Object extractParams(String token, String key) {
        return extractAllClaims(token).get(key);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("email", customUserDetails.getEmail());
            claims.put("firstName", customUserDetails.getFirstName());
            claims.put("secondName", customUserDetails.getSecondName());
            claims.put("role", customUserDetails.getRole());
            claims.put("permission", getAllPermission());
        }
        return generateToken(claims, userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
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

    private List<String> getAllPermission() {
        return List.of(USER_OWNER, WALLET_OWNER, ITEM_OWNER, TRANSFER);
    }
}
