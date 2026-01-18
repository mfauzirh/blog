package com.oldcatlabs.blog.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: get this from prop file
        return User.builder()
                .username(username)
                .password("$2a$12$K8s8eOv41dAtzvD4ji8Z8eaAO96N61DQEPAOfLPp83HotiqvbW3q.")
                .build();
    }

}
