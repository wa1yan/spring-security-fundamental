package dev.waiyanhtet.spring_security_lesson3.config.security.provider;

import dev.waiyanhtet.spring_security_lesson3.config.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${auth.secret.key.value}")
    private String configSecretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        var headerKey = customAuthentication.getKey();

        if(headerKey.equals(configSecretKey)) {
            return new CustomAuthentication(true, "");
        }

        throw new BadCredentialsException("Oh No!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
