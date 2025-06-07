package com.mrsl7.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity    // для @PreAuthorize
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключаем CSRF
                .csrf(csrf -> csrf.disable())

                // Права доступа
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/*/roles").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // Basic Auth без deprecated
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOp — сравнивает «как есть». В проде замените на BCryptPasswordEncoder
        return NoOpPasswordEncoder.getInstance();
    }
/*
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // В целях тестирования: два пользователя — обычный USER и админ
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user123")       // {noop} — означает «нет шифрования»
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("USER", "ADMIN")          // одновременно USER и ADMIN
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }*/
}