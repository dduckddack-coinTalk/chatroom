package com.example.kointalkchatroom.monkey;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String username;
    private String avatar;

    public static User systemUser(){
        return new User("System", "https://robohash.org/system.png");
    }

    @JsonCreator
    public User(@JsonProperty("username") String username, @JsonProperty("avatar") String avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public String getAlias() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

}
