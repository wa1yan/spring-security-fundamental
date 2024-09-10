package dev.waiyanhtet.spring_security_lesson2.service;

import dev.waiyanhtet.spring_security_lesson2.repository.UserRepository;
import dev.waiyanhtet.spring_security_lesson2.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOpt =  userRepository.findUserByUsername(username);

        return userOpt.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found" + username));
    }
}
