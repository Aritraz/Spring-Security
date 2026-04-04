package com.tutorial.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain config(HttpSecurity security)
    {
        return security
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                .build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager()
//    {
//        System.out.println("I am inside authenticationManager");
//        return new ProviderManager(provider());
//
//
//    }
    @Bean
    public AuthenticationProvider provider()
    {
        System.out.println("I am inside authenticationProvider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());//Important ! Otherwise exception
        //will be thrown
        return provider;
    }

}
