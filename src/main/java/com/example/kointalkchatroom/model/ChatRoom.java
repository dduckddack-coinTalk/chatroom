package com.example.kointalkchatroom.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

public void handleActions(WebSocketSession session, Event event, ChatService chatService, Flux<WebSocketMessage> webSocketMessage) {
        if(event.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
//            event.setMessage(event.getSender() + "님이 입장했습니다;");
        }
        sendMessage(event, chatService, webSocketMessage);
    }

    public <T> void sendMessage(T message, ChatService chatService, Flux<WebSocketMessage> webSocketMessage){
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message, webSocketMessage));
    }


}
