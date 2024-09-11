package dev.waiyanhtet.spring_security_lesson3.config.security.filter;

import dev.waiyanhtet.spring_security_lesson3.config.security.authentication.CustomAuthentication;
import dev.waiyanhtet.spring_security_lesson3.config.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var headerKey = request.getHeader("key");
        var customAuthentication = new CustomAuthentication(false, headerKey);
        var authenticationResult = customAuthenticationManager.authenticate(customAuthentication);

        if(authenticationResult.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            filterChain.doFilter(request, response);
        }
    }
}
