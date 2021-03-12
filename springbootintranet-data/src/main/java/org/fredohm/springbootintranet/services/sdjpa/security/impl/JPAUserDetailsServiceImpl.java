package org.fredohm.springbootintranet.services.sdjpa.security.impl;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.repositories.security.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JPAUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("User name " + username + "not found");
        });
    }
}
