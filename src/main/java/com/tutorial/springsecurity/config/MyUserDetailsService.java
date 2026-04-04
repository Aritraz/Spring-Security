package com.tutorial.springsecurity.config;

import com.tutorial.springsecurity.model.User;
import com.tutorial.springsecurity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user =  userRepository.findByUsername(username) ;

       if(user == null)
       {
           throw new UsernameNotFoundException("No User Found");
       }
       return new UserPrinciple(user);
    }
}
