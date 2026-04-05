package com.tutorial.springsecurity.service;

import com.tutorial.springsecurity.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;


import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {

    HashMap<String , Object> claims = new HashMap<>();
    //claims will be used later , if we need to add any other properties in the jwt token
    public String getToken(User user)
    {
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*40))
                .signWith(getSigningKey())
                .compact();
    }

    public Key getSigningKey()
    {
        try {
             KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
             return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
