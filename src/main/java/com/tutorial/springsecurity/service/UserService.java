package com.tutorial.springsecurity.service;

import com.tutorial.springsecurity.dto.UserRegistrationRequest;
import com.tutorial.springsecurity.model.User;
import com.tutorial.springsecurity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User registerUser(UserRegistrationRequest user)
    {
        User registerUser = new User();

        registerUser.setUserId(user.getUserid());
        registerUser.setPassword(encoder.encode(user.getPassword()));
        registerUser.setUsername(user.getUsername());

        if(user.getRole().equalsIgnoreCase("ADMIN"))
        {
            //Register User with ADMIN , USER role
            registerUser.setRoles(Arrays.asList("ADMIN","USER"));

        }

        //Register User with USER role
        else {

            registerUser.setRoles(Arrays.asList("USER"));
        }
        userRepository.save(registerUser);
        return registerUser;

    }

    public String loginUser(User user)
    {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getUsername() , user.getPassword());


        Authentication authObject = authenticationManager.authenticate(token);

        if(authObject.isAuthenticated())
        {
            //TODO
            //USER AUTHENTICATED GENERATE TOKEN
            return jwtService.getToken(user);

        }
        else {
            //TODO
            //USER NOT AUTHENTICATED RETURN EXCEPTION
        }

            return null;
    }


}
