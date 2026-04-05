package com.tutorial.springsecurity.config;

import com.tutorial.springsecurity.filter.JwtFilter;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain config(HttpSecurity security) throws Exception {

        return security
                .formLogin(cust -> cust.disable())
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(requst ->
                        requst.requestMatchers("/login").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager()
    {
       // System.out.println("I am inside authenticationManager");
        return new ProviderManager(provider());

    }

    @Bean
    public AuthenticationProvider provider()
    {
        //System.out.println("I am inside authenticationProvider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService());
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());//Important ! Otherwise exception
        //will be thrown

        //Changing No-op password encoder to Bycrypt , as we are using Bcrypt to encode password
        //While registering.

        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public UserDetailsService userDetailsService()
    {
        return new MyUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }


}
