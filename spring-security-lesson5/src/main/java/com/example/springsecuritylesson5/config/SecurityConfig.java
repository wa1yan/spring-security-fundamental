package com.example.springsecuritylesson5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(req ->
                        //req.anyRequest().permitAll())
                        //req.anyRequest().denyAll())
                        //req.anyRequest().hasAuthority("read"))
                        //req.anyRequest().hasAnyAuthority("read", "write"))
                        //req.anyRequest().hasRole("ADMIN"))
                        //req.anyRequest().hasAnyRole("ADMIN", "MANAGER"))
                        //req.anyRequest().hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER"))
                        //req.anyRequest().access("isAuthenticated() and hasAuthority('read')") //SpEL
                        req.requestMatchers("/greet").hasAuthority("read"))
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        var userDetail = new InMemoryUserDetailsManager();
        var william = User.withUsername("william")
                .password(passwordEncoder().encode("password"))
                .authorities("write")
                //.roles("ADMIN")
                .build();

        var suzan = User.withUsername("suzan")
                .password(passwordEncoder().encode("password"))
                .authorities("read")
               // .roles("MANAGER")
                .build();

        userDetail.createUser(william);
        userDetail.createUser(suzan);


        return userDetail;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
