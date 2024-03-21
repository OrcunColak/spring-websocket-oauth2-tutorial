package com.colak.springwebsockettutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        // a user should be authorized and have the “notifications.read” scope in order to allow
                        // a request to /ws/notifications to be processed.
                        .requestMatchers("/ws/notifications").hasAuthority("SCOPE_notifications.read")

                        // Permit all POST requests to /notifications/** endpoint to allow sending notifications
                        .requestMatchers(HttpMethod.POST, "/notifications/**").permitAll()
                )
                .oauth2ResourceServer(configurer -> configurer
                        .jwt(Customizer.withDefaults())
                )
                .build();
    }

}
