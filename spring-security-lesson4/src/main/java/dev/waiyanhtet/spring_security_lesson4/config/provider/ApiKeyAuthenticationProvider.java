package dev.waiyanhtet.spring_security_lesson4.config.provider;

import dev.waiyanhtet.spring_security_lesson4.config.authentication.ApiKeyAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private final String configToken;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication auth = (ApiKeyAuthentication) authentication;
        var headerToken = auth.getHeaderToken();

        if(configToken.equals(headerToken)) {
            auth.setAuthentication(true);
            return auth;
        }

        throw new BadCredentialsException("Oh No!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
