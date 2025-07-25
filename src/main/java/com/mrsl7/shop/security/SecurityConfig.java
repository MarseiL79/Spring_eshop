package com.mrsl7.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JpaUserDetailsService uds;

    public SecurityConfig(JpaUserDetailsService uds) {
        this.uds = uds;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(uds);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authProvider())

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // регистрация открыта
                        .requestMatchers(HttpMethod.POST, "/api/users", "/api/users/register")
                        .permitAll()
                        // роль ADMIN может менять роли
                        .requestMatchers(HttpMethod.PUT, "/api/users/*/roles")
                        .hasRole("ADMIN")
                        // главная и статика без логина
                        .requestMatchers("/", "/index.html", "/static/**")
                        .permitAll()
                        // всё остальное требует аутентификации
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
