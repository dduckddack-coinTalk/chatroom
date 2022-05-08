package com.example.kointalkchatroom.handler;

import com.example.kointalkchatroom.model.ChatMessage;
import com.example.kointalkchatroom.model.ChatRoom;
import com.example.kointalkchatroom.model.ChatService;
import com.example.kointalkchatroom.model.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@NoArgsConstructor
@Service
public class WebFluxWebSocketHandler implements WebSocketHandler {
    private ObjectMapper objectMapper;
    private ChatService chatService;

    private Flux<String> outputEvents;

    public WebFluxWebSocketHandler(Flux<Event> event, ChatService chatService) {
        this.objectMapper = new ObjectMapper();
        this.outputEvents = Flux.from(event).map(this::toJSON);
        this.chatService = chatService;
    }


    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .log()
                .map(this::toEvent)
                .doOnNext(it -> {
                    ChatRoom chatRoom = chatService.findRoombyId(it.getId());
                    chatRoom.handleActions(session, it, chatService, outputEvents.map(session::textMessage));
                })
                .zipWith(session.send(outputEvents.map(session::textMessage)))
                .then();

    }

    private Event toEvent(String json) {
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON:" + json, e);
        }
    }

    private String toJSON(Event event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}