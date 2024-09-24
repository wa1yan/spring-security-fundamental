package dev.waiyanhtet.springsecuritylesson7.security;

import org.springframework.stereotype.Component;

@Component
public class CustomSecurityFilter {

    public boolean condition() {
        return true;
    }
}
