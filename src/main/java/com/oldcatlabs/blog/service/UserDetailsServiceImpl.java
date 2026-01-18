package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.properties.SecretProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SecretProperties secretProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || !username.equals(secretProperties.getUserUsername()))
            throw new UsernameNotFoundException("invalid username or password");

        // TODO: get this from prop file
        return User.builder()
                .username(username)
                .password(secretProperties.getUserPassword())
                .build();
    }

}
