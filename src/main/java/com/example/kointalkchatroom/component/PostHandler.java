package com.example.kointalkchatroom.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class PostHandler {


    public Mono<ServerResponse> hello(ServerRequest serverRequest) {
        return ServerResponse.ok().body(Mono.just("hello chatting!"), String.class);
    }
}
