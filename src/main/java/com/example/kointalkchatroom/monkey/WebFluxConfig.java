package com.example.kointalkchatroom.monkey;

import com.example.kointalkchatroom.monkey.ChatSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

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
                .replay(25)
                .autoConnect();
    }

    @Bean
    public HandlerMapping webSocketMapping(UnicastProcessor<Event> eventPublisher, Flux<Event> events) {
        log.info("master 서버 입니다.");
        Map<String, Object> map = new HashMap<>();
        map.put("/chatting/rs", new ChatSocketHandler(eventPublisher, events));
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setUrlMap(map);

        //Without the order things break :-/
        simpleUrlHandlerMapping.setOrder(10);
        return simpleUrlHandlerMapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public UserStats userStats(Flux<Event> events, UnicastProcessor eventPublisher){
        return new UserStats(events, eventPublisher);
    }
}
