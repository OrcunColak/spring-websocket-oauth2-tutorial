package com.colak.springwebsockettutorial.config;

import com.colak.springwebsockettutorial.handler.CustomWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Bean("sessions")
    public Map<String, WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new CustomWebSocketHandler(sessions), "/ws/notifications");
    }
}