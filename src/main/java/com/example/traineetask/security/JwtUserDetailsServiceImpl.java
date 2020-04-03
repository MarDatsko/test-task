package com.example.traineetask.security;

import com.example.traineetask.entity.User;
import com.example.traineetask.service.UserService;
import com.example.traineetask.service.impl.UserServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.getByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
        grantedAuthorities.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
