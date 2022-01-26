package com.mpiskorz.github.addon.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

public class UserDeserializerTest {

    private static final String VALID_GITHUB_USER_JSON =
            "{\n" +
            "  \"login\": \"piskorzm\",\n" +
            "  \"id\": 29603920,\n" +
            "  \"node_id\": \"MDQ6VXNlcjI5NjAzOTIw\",\n" +
            "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/29603920?v=4\",\n" +
            "  \"gravatar_id\": \"\",\n" +
            "  \"url\": \"https://api.github.com/users/piskorzm\",\n" +
            "  \"html_url\": \"https://github.com/piskorzm\",\n" +
            "  \"followers_url\": \"https://api.github.com/users/piskorzm/followers\",\n" +
            "  \"following_url\": \"https://api.github.com/users/piskorzm/following{/other_user}\",\n" +
            "  \"gists_url\": \"https://api.github.com/users/piskorzm/gists{/gist_id}\",\n" +
            "  \"starred_url\": \"https://api.github.com/users/piskorzm/starred{/owner}{/repo}\",\n" +
            "  \"subscriptions_url\": \"https://api.github.com/users/piskorzm/subscriptions\",\n" +
            "  \"organizations_url\": \"https://api.github.com/users/piskorzm/orgs\",\n" +
            "  \"repos_url\": \"https://api.github.com/users/piskorzm/repos\",\n" +
            "  \"events_url\": \"https://api.github.com/users/piskorzm/events{/privacy}\",\n" +
            "  \"received_events_url\": \"https://api.github.com/users/piskorzm/received_events\",\n" +
            "  \"type\": \"User\",\n" +
            "  \"site_admin\": false,\n" +
            "  \"name\": \"Marcin Piskorz\",\n" +
            "  \"company\": \"Sollers Consulting\",\n" +
            "  \"blog\": \"\",\n" +
            "  \"location\": \"Poznan\",\n" +
            "  \"email\": null,\n" +
            "  \"hireable\": null,\n" +
            "  \"bio\": \"Uni of York MEng of CS\",\n" +
            "  \"twitter_username\": null,\n" +
            "  \"public_repos\": 16,\n" +
            "  \"public_gists\": 0,\n" +
            "  \"followers\": 4,\n" +
            "  \"following\": 5,\n" +
            "  \"created_at\": \"2017-06-21T13:44:49Z\",\n" +
            "  \"updated_at\": \"2022-01-01T15:42:13Z\"\n" +
            "}";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldDeserializeUserAndApplyCalculations() throws JsonProcessingException {
        // given
        User expectedUser = new User(29603920,
                "piskorzm",
                "Marcin Piskorz",
                "User",
                "https://avatars.githubusercontent.com/u/29603920?v=4",
                Instant.parse("2017-06-21T13:44:49Z"),
                27.0);

        // when
        User parsedUser = mapper.readValue(VALID_GITHUB_USER_JSON, User.class);

        // then
        assertThat(parsedUser).usingRecursiveComparison().isEqualTo(expectedUser);
    }
}
