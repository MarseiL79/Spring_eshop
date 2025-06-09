package com.mrsl7.shop.security;

import com.mrsl7.shop.entity.Role;
import com.mrsl7.shop.entity.User;
import com.mrsl7.shop.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    public JpaUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>> loadUserByUsername: " + username);
        User u = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println(">>> user: " + u.getUsername());
        System.out.println(">>> user: " + u.getPasswordHash());
        System.out.println(">>> user: " + u.getRoles());

        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getUsername())
                .password(u.getPasswordHash())
                .roles(u.getRoles().stream()
                        .map(Role::getName)
                        .toArray(String[]::new))
                .build();
    }
}
