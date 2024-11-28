package com.communicare.CommuniCareBackend.Application.config;

import com.communicare.CommuniCareBackend.Domain.service.OurEmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private OurEmployeeDetailsService ourEmployeeDetailsService;

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    // Security configuration for both Web and Mobile apps
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless authentication
                .cors(Customizer.withDefaults()) // Enable CORS (Global for both Web and Mobile)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**", "/public/**").permitAll()  // Web and Mobile (Sign-up, Login)
                        // Web app specific access control
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("USER")
                        .requestMatchers("/adminuser/**").hasAnyAuthority("ADMIN", "USER")
                        // Mobile app specific access control (ensure proper paths are added here)
                        .requestMatchers("/app/api/users/sign-up", "/app/api/users/login").permitAll()  // Mobile-specific sign-up/login
                        .requestMatchers("/app/api/**").permitAll()
                        // Allow any other request for web and mobile (Open access to any URL)
                        //.anyRequest().permitAll() // Any other request is also permitted (open access)
                        .anyRequest().authenticated() // Any other request requires authentication
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter for both apps

        return http.build();
    }

    // Authentication provider for DAO-based authentication
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(ourEmployeeDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // Password encoder for both Web and Mobile apps
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication manager for Web and Mobile
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
