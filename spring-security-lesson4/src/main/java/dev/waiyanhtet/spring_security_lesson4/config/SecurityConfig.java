package dev.waiyanhtet.spring_security_lesson4.config;

import dev.waiyanhtet.spring_security_lesson4.config.filter.ApiKeyFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${secret.key}")
    private String configToken;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new ApiKeyFilter(configToken), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .build();
    }
}
