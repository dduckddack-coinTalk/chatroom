package com.example.kointalkchatroom.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public Mono<List<ChatRoom>> findAllRoom() {
        return Mono.just(new ArrayList<>(chatRooms.values()));
    }

    public ChatRoom findRoombyId(int roomId) {
        return chatRooms.get(roomId);
    }

    public Mono<ChatRoom> createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder().roomId(randomId).name(name).build();
        chatRooms.put(randomId, chatRoom);
        return Mono.just(chatRoom);
    }

    public <T> void sendMessage(WebSocketSession session, T message, Flux<WebSocketMessage> webSocketMessage) {
        try {
            session.send(webSocketMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
