package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("spring.application.security.jwt.expiration")
    private long JWTRxpiration;

@Value("${spring.application.security.jwt.secret-key}")
    private  String SECRET_KEY ;
    public <T> T extractclaims( String token , Function<Claims,T> claimsresolver){
        Claims claims=getallclaims(token);
        return claimsresolver.apply(claims);
    }
    private Key getSignInKey() {
        byte[] KeyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(KeyBytes);
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
    private Claims getallclaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }
    public String generatetoken(UserDetails userDetails, Map<String, Object> claims){
        return buildtoken(claims,userDetails,JWTRxpiration);
    }

    private String buildtoken(Map<String, Object> claims
            , UserDetails userDetails,
                              long jwtRxpiration) {
        var authereties=userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
         return  Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+jwtRxpiration)).claim("authoroties",authereties)
                .signWith(getSignInKey()).compact();
    }
}
