package dev.waiyanhtet.spring_security_lesson2.security;

import dev.waiyanhtet.spring_security_lesson2.entities.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
