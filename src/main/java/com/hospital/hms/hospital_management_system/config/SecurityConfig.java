package com.hospital.hms.hospital_management_system.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;


    // =========================
    // Password Encoder
    // =========================

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(12);
    }


    // =========================
    // Security Configuration
    // =========================

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http.csrf(
                customizer ->
                        customizer.disable()
        );


        http.cors(
                Customizer.withDefaults()
        );


        http.authorizeHttpRequests(
                request -> request


                // =========================
                // PUBLIC ENDPOINTS
                // =========================

                .requestMatchers(
                        "/register",
                        "/register/doctor",
                        "/register/patient",
                        "/login"
                )
                .permitAll()


                // =========================
                // PUBLIC DOCTOR LIST
                // =========================

                .requestMatchers(
                        "/doctors",
                        "/doctors/{id}",
                        "/doctors/{id}/image"
                )
                .permitAll()


                // =========================
                // LOGGED-IN DOCTOR PROFILE
                // =========================

                .requestMatchers(
                        "/doctors/me"
                )
                .hasRole("DOCTOR")


                // =========================
                // APPOINTMENTS
                // =========================

                .requestMatchers(
                        "/appoinments/**"
                )
                .authenticated()


                // =========================
                // ADMIN
                // =========================

                .requestMatchers(
                        "/admin/**"
                )
                .hasRole("ADMIN")


                // =========================
                // DOCTOR
                // =========================

                .requestMatchers(
                        "/doctor/**"
                )
                .hasRole("DOCTOR")


                // =========================
                // PATIENT
                // =========================

                .requestMatchers(
                        "/patient/**"
                )
                .hasRole("PATIENT")


                // =========================
                // EVERYTHING ELSE
                // =========================

                .anyRequest()
                .authenticated()

        );


        // JWT Filter

        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );


        // HTTP Basic

        http.httpBasic(
                Customizer.withDefaults()
        );


        return http.build();
    }


    // =========================
    // Authentication Provider
    // =========================

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setPasswordEncoder(
                passwordEncoder()
        );

        provider.setUserDetailsService(
                userDetailsService
        );

        return provider;
    }


    // =========================
    // Authentication Manager
    // =========================

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }


    // =========================
    // CORS
    // =========================

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();


        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost:5173"
                )
        );


        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );


        configuration.setAllowedHeaders(
                List.of("*")
        );


        configuration.setAllowCredentials(
                true
        );


        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();


        source.registerCorsConfiguration(
                "/**",
                configuration
        );


        return source;
    }
}