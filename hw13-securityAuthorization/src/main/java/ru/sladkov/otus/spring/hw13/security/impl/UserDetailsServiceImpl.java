package ru.sladkov.otus.spring.hw13.security.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw13.domain.User;
import ru.sladkov.otus.spring.hw13.domain.UserRole;
import ru.sladkov.otus.spring.hw13.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username" + username + "was not found"));

        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), authorities);
    }
}
