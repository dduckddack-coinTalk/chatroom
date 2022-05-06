package com.example.kointalkchatroom.component;

import com.example.kointalkchatroom.dto.ChatRoom;
import com.example.kointalkchatroom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PostHandler {

    private final ChatRoomRepository chatRoomRepository;

    public Mono<ServerResponse> hello(ServerRequest serverRequest) {
        return ServerResponse.ok().body(Mono.just("hello chatting!"), String.class);
    }

    public Mono<ServerResponse> getRooms(ServerRequest serverRequest) {
        Mono<List<ChatRoom>> response = Mono.just(chatRoomRepository.findAllRoom());

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, ChatRoom.class);
    }


}
