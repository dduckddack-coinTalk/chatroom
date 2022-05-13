package com.example.kointalkchatroom.config;

import com.example.kointalkchatroom.Handler.ChatSocketHandler;
import com.example.kointalkchatroom.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class WebFluxConfig {

    @Bean
    public UnicastProcessor<Event> eventPublisher(){
        return UnicastProcessor.create();
    }

    @Bean
    public Flux<Event> events(UnicastProcessor<Event> eventPublisher) {
        return eventPublisher
                .replay(500)
                .autoConnect();
    }

    @Bean
    public HandlerMapping webSocketMapping(UnicastProcessor<Event> eventPublisher, Flux<Event> events) {
        Map<String, Object> map = new HashMap<>();
        map.put("/chatting/rs", new ChatSocketHandler(eventPublisher, events));
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setUrlMap(map);

        simpleUrlHandlerMapping.setOrder(0);
        return simpleUrlHandlerMapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }


}
