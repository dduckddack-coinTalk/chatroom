package com.example.kointalkchatroom.config;

import com.example.kointalkchatroom.component.WebFluxWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import java.util.Map;

@Configuration
public class WebFluxConfig {

    @Autowired
    private WebFluxWebSocketHandler handler;

    @Bean
    public HandlerMapping handlerMapping(){
        Map<String, WebFluxWebSocketHandler> handlerMap = Map.of(
                "/uppercase", handler
        );
        return new SimpleUrlHandlerMapping(handlerMap, 1);
    }




//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/chatting/ws-stomp")
//                .setAllowedOrigins(
//                        "https://cointalk.wachsenhaus.com",
//                        "http://3.90.102.135:8080",
//                        "http://localhost:8080",
//                        "http://localhost:3000",
//                        "https://cointalk.kro.kr:8080"
//                        )
//                .withSockJS();
//    }
}