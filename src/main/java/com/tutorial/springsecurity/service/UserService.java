package com.tutorial.springsecurity.service;

import com.tutorial.springsecurity.model.User;
import com.tutorial.springsecurity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User registerUser(User user)
    {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public String loginUser(User user)
    {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getUsername() , user.getPassword());


        Authentication authObject = authenticationManager.authenticate(token);

        if(authObject.isAuthenticated())
        {
            User existingUser = userRepository.findByUsername(user.getUsername());
            HashMap<String , Object> claims = new HashMap<>();
            claims.put("role",existingUser.getRole());
            //TODO
            //USER AUTHENTICATED GENERATE TOKEN
            return jwtService.getToken(existingUser);

        }
        else {
            //TODO
            //USER NOT AUTHENTICATED RETURN EXCEPTION
        }

            return null;
    }


}
