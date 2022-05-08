package com.example.kointalkchatroom.config;

import com.example.kointalkchatroom.handler.WebFluxWebSocketHandler;
import com.example.kointalkchatroom.model.ChatMessage;
import com.example.kointalkchatroom.model.ChatService;
import com.example.kointalkchatroom.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class WebFluxConfig {

    @Autowired
    private ChatService chatService;


    @Bean
    public HandlerMapping webSocketMapping(Flux<Event> event){
        Map<String, WebFluxWebSocketHandler> handlerMap = new HashMap<>();
//        Map<String, WebFluxWebSocketHandler> handlerMap = Map.of(
//                "/uppercase", new WebFluxWebSocketHandler(eventPublisher,chatMessage, chatService)
//        );
        return new SimpleUrlHandlerMapping(handlerMap, 1);
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

}
