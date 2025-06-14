package ru.tihomirov.java_bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/jokes/**").hasAuthority("JOKE_READ")

                        .requestMatchers(HttpMethod.POST, "/api/jokes/**").hasAuthority("JOKE_WRITE")

                        .requestMatchers(HttpMethod.PUT, "/api/jokes/**").hasAuthority("JOKE_WRITE")
                        .requestMatchers(HttpMethod.DELETE, "/api/jokes/**").hasAuthority("JOKE_DELETE")

                        .requestMatchers("/admin/**").hasAuthority("USER_MANAGE")

                        .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
