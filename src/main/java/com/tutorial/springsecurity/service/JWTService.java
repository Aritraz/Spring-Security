package com.tutorial.springsecurity.service;

import com.tutorial.springsecurity.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {

    //claims will be used later , if we need to add any other properties in the jwt token
    HashMap<String , Object> claims = new HashMap<>();

    private static SecretKey secretKey;

    public JWTService()
    {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            secretKey=keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getToken(User user)
    {


        this.claims.put("role",user.getRole());
        System.out.println("ROLES "+ user.getRole());
        return Jwts.builder()
                .claim("claims",claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*40*100))
                .signWith(getSigningKey())
                .compact();

    }

    public SecretKey getSigningKey()
    {
       return secretKey;
    }

    public Claims extractClaims(String token) {
      return   Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String extractUserNameFromToken(String token)
    {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String userName, String token, UserDetails user) {
       if(user.getUsername().equalsIgnoreCase(userName) && extractClaims(token).getExpiration().after(new Date()) )
       {
           return true;
       }

       return false;
    }
}
