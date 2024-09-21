package com.example.springsecuritylesson6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req.requestMatchers(HttpMethod.GET, "/greet/**").hasAuthority("read"))
                .authorizeHttpRequests(req -> req.requestMatchers(HttpMethod.POST, "/greet/**").hasAuthority("write"))
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable) //DON'T DO IN PROD
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("william")
                .password(passwordEncoder().encode("password"))
                .authorities("read")
                .build();
        var user2 = User.withUsername("suzan")
                .password(passwordEncoder().encode("password"))
                .authorities("write")
                .build();
        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);
        return userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
