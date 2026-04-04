package com.tutorial.springsecurity.config;

import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity security)
    {
        return security
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager()
    {
        System.out.println("I am inside authenticationManager");
        return new ProviderManager(provider());


    }
    @Bean
    public AuthenticationProvider provider()
    {
        System.out.println("I am inside authenticationProvider");
        return new DaoAuthenticationProvider(userDetailsService());
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        System.out.println("I am inside userDetailsService");
      UserDetails userOne = User
              .withDefaultPasswordEncoder()
              .password("Aritra@1234")
              .username("Aritra")
              .build();

        return new InMemoryUserDetailsManager(userOne);
    }
}
