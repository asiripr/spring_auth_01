package com.security.exercise_01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.exercise_01.models.Users;
import com.security.exercise_01.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;
    
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    private JWTService jwtService;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(Users user) {
        // Use Spring Security's Authentication here
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "Failed";
        }
    }
}