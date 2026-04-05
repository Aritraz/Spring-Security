package com.tutorial.springsecurity.filter;

import com.tutorial.springsecurity.config.MyUserDetailsService;
import com.tutorial.springsecurity.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if(authHeader !=null && authHeader.startsWith("Bearer ") )
        {
        token = authHeader.substring(7);
        userName = jwtService.extractUserNameFromToken(token);
        UserDetails user = null;
        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            try
            {
                user = userDetailsService.loadUserByUsername(userName);
            }catch (UsernameNotFoundException ex)
            {
                System.out.println("User Not Found");
            }
            if(jwtService.validateToken(userName,token,user))
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user , null , user.getAuthorities());

                //Adding the whole request in the security context , so that later
                // we can fetch additional details from request inside security context
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        }
        filterChain.doFilter(request,response);

    }
}
