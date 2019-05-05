package com.frankc137.jinote.security.impl;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

public class CustomizeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomizeAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);

        if (userDetails != null) {
            if (bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new GrantedAuthorityProxy("ROLE_ADMIN"));
                authorities.add(new GrantedAuthorityProxy("AUTH_WRITE"));
                Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
                return auth;
            } else {
                throw new BadCredentialsException("incorrect password");
            }
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
