package com.example.kointalkchatroom.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.rsocket.Payload;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

    @JsonCreator
    public ChatMessage(@JsonProperty("type") MessageType type,
                 @JsonProperty("roomId") String roomId,
                 @JsonProperty("sender") String sender,
                 @JsonProperty("message") String message
                       ) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

}
