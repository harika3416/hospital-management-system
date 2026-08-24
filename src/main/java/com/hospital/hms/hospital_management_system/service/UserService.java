package com.hospital.hms.hospital_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.hms.hospital_management_system.model.Users;
import com.hospital.hms.hospital_management_system.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public Users register(Users user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repo.save(user);
    }

    public String verify(Users user) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );

        if (authentication.isAuthenticated()) {

            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();

            return jwtService.generateToken(
                    userDetails
            );
        }

        return "Login Failed";
    }
}