package com.ahmed.secoopecproductnetwork.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@CacheEvict

public class JWTService {

    @Value("${application.security.jwt.expiration}")
    private long JWTRxpiration;

    @Value("${application.security.jwt.secret-key}")
    private  String SECRET_KEY ;
    public <T> T extractclaims( String token , Function<Claims,T> claimsresolver){
        Claims claims=getallclaims(token);
        return claimsresolver.apply(claims);
    }
    private Key getSignInKey() {
        // Decode the base64-encoded secret key
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        // Create a signing key from the decoded bytes
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractusername(String token) {
        return  extractclaims(token,Claims::getSubject);
    }
    public boolean isTokenvalid(String token,UserDetails userDetails){
        final String username=extractusername(token);
        return (username.equals(userDetails.getUsername() )) && !isTokenExpired(token);

    }
    private boolean isTokenExpired(String token) {
        return extractxpiration(token).before(new Date());
    }
    private Date extractxpiration(String token) {
        return extractclaims(token,Claims::getExpiration);
    }

    private Claims getallclaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .setAllowedClockSkewSeconds(60) // Allow 60 seconds of clock skew
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String generatetoken(UserDetails userDetails, Map<String, Object> claims){
        return buildtoken(claims,userDetails,JWTRxpiration);
    }

    private String buildtoken(Map<String, Object> claims, UserDetails userDetails, long jwtExpirationMillis) {
        var authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpirationMillis);

        System.out.println("Token issued at: " + now);
        System.out.println("Token expires at: " + expiration);
        System.out.println("Expiration: " + expiration);
        System.out.println("Current Time: " + new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .claim("authorities", authorities)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

}