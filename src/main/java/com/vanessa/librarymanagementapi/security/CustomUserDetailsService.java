package com.vanessa.librarymanagementapi.security;

import com.vanessa.librarymanagementapi.user.model.User;
import com.vanessa.librarymanagementapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = service.getByLogin(login);
        if (user == null){
            throw new UsernameNotFoundException("User not found.");
        }
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword()).roles(user.getRoles().toArray(new String[user.getRoles().size()]))
                .build();
    }
}