package dev.waiyanhtet.spring_security_lesson4.config.filter;

import dev.waiyanhtet.spring_security_lesson4.config.authentication.ApiKeyAuthentication;
import dev.waiyanhtet.spring_security_lesson4.config.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String configToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomAuthenticationManager manager = new CustomAuthenticationManager(configToken);
        String headerToken = request.getHeader("x-api-key");

        if(!StringUtils.hasText(headerToken)) {
            filterChain.doFilter(request, response);
        }

        var authentication = new ApiKeyAuthentication(headerToken);

        try {
            var authResponse = manager.authenticate(authentication);

            if(authResponse.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authResponse);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
