package com.example.kointalkchatroom.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSockConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/chatting/sub");
        config.setApplicationDestinationPrefixes("/chatting/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chatting/ws-stomp")
                .setAllowedOrigins("https://cointalk.wachsenhaus.com",
                        "http://3.90.102.135:8080",
                        "http://localhost:8080",
                        "http://localhost:3000",
                        "https://cointalk.kro.kr:8080"
                        )
                .withSockJS();
    }
}