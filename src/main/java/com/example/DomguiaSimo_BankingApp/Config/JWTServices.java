package com.example.DomguiaSimo_BankingApp.Config;

import com.example.DomguiaSimo_BankingApp.User.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;

@Configuration
public class JWTServices {

    private static final String SECRET_KEY = "122345566788995122345566788995122345566788995";

    public String extractEmail(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10 ))
                .signWith( getSignInKey())
                .compact();
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
//        return (extractEmail(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
        return (extractEmail(token).equals(userDetails.getUsername()) );

    }

//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
