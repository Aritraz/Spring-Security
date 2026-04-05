package com.tutorial.springsecurity.service;

import com.tutorial.springsecurity.model.User;
import com.tutorial.springsecurity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User registerUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
