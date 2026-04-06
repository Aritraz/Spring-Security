package com.tutorial.springsecurity.controller;

import com.tutorial.springsecurity.dto.UserRegistrationRequest;
import com.tutorial.springsecurity.model.User;
import com.tutorial.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserRegistrationRequest user)
    {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        return userService.loginUser(user);
    }
}
