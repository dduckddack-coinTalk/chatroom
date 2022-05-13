package com.example.kointalkchatroom.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Payload {

    private String roomId;

    private User user;

    private Map<String, Object> properties = new HashMap<>();

    public Payload(String roomId, User user, Map<String, Object> properties){
        this(roomId, user);
        this.properties = properties;
    }
    @JsonCreator
    private Payload(@JsonProperty("roomId") String roomId, @JsonProperty("user") User user) {
        this.roomId = roomId;
        this.user = user;
    }

    @JsonAnySetter
    private void setProperties(String name, Object value){
        properties.put(name, value);
    }

    @JsonAnyGetter
    private Map<String, Object> getProperties(){
        return properties;
    }

}