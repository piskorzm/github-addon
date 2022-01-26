package com.mpiskorz.github.addon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;

@JsonDeserialize(using = UserDeserializer.class)
public class User {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("login")
    String login;

    @JsonProperty("name")
    String name;

    @JsonProperty("type")
    String type;

    @JsonProperty("avatarUrl")
    String avatarUrl;

    @JsonProperty("createdAt")
    Instant createdAt;

    @JsonProperty("calculations")
    Double calculations;

    public User(Integer id, String login, String name, String type, String avatarUrl, Instant createdAt, Double calculations) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
    }
}
