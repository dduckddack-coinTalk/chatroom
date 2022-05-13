package com.example.kointalkchatroom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String username;
    private String avatar;

    @JsonCreator
    public User(@JsonProperty("username") String username, @JsonProperty("avatar") String avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

}
