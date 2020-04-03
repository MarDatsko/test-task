package com.example.traineetask.controller;

import com.example.traineetask.dto.UserSingUpDto;
import com.example.traineetask.entity.User;
import com.example.traineetask.enums.UserRole;
import com.example.traineetask.security.JwtUserDetailsServiceImpl;
import com.example.traineetask.security.JwtUtil;
import com.example.traineetask.security.entity.AuthenticationRequest;
import com.example.traineetask.security.entity.AuthenticationResponse;
import com.example.traineetask.service.UserService;
import com.example.traineetask.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityController(AuthenticationManager authenticationManager,
                              JwtUserDetailsServiceImpl userDetailsService,
                              JwtUtil jwtUtil, UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final AuthenticationResponse jwt = new AuthenticationResponse(jwtUtil.createToken(userDetails));
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> addUser(@RequestBody UserSingUpDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        user.setLastVisit(LocalDateTime.now());
        user.setDateOfRegistration(LocalDateTime.now());

        userService.create(user);
        return ResponseEntity.ok("User created successfully");
    }
}
