package com.tutorial.springsecurity.service;

import com.tutorial.springsecurity.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;


import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class JWTService {

    public String getToken(User user)
    {
        return Jwts.builder()
                .claims()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*40))
                .and()
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
