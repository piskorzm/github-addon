package com.mpiskorz.github.addon.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.Instant;

public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser jp, DeserializationContext dCtx) throws IOException {

        JsonNode node = jp.getCodec().readTree(jp);

        int id = node.get("id").asInt();
        String login = node.get("login").asText();
        String name = node.get("name").asText();
        String type = node.get("type").asText();
        String avatarUrl = node.get("avatar_url").asText();
        Instant createdAt = Instant.parse(node.get("created_at").asText());

        int followers = node.get("followers").asInt();
        int publicRepos = node.get("public_repos").asInt();
        Double calculations = 6.0 / followers * (2 + publicRepos);

        return new User(id, login, name, type, avatarUrl, createdAt, calculations);
    }
}
