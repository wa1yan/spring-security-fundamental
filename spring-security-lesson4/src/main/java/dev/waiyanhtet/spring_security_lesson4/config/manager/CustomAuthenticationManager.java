package dev.waiyanhtet.spring_security_lesson4.config.manager;

import dev.waiyanhtet.spring_security_lesson4.config.provider.ApiKeyAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final String configKey;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthenticationProvider authenticationProvider = new ApiKeyAuthenticationProvider(configKey);
        if(authenticationProvider.supports(authentication.getClass())) {
            return authenticationProvider.authenticate(authentication);
        }
        throw new BadCredentialsException("Oh No!");
    }
}
