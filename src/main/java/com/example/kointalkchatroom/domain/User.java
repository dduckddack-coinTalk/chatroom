package com.example.kointalkchatroom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class User {

    private long userId;
    private String username;
    private String avatar;

    @JsonCreator
    public User(@JsonProperty("username") String username, @JsonProperty("userId") long userId, @JsonProperty("avatar") String avatar) {
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
    }



}
