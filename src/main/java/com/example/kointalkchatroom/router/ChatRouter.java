package com.example.kointalkchatroom.router;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RestController
public class ChatRouter {

    @Bean
    public RouterFunction<ServerResponse> chattingRouter() {
        return route().nest(path("/chatting"), builder -> {
            builder.GET("/test", (req) -> ServerResponse.ok().body(Mono.just("hello chatting!"), String.class));
        }).build();
    }
}
