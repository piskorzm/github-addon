package com.mpiskorz.github.addon.service;

import com.mpiskorz.github.addon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class GithubService {

    private static final String GITHUB_URL = "https://api.github.com";
    private static final String USERS_PATH = "users";

    @Autowired
    public GithubService() {

    }

    public User getUser(String login) {
        URI uri = UriComponentsBuilder
                .fromUriString(GITHUB_URL)
                .pathSegment(USERS_PATH, login)
                .build().toUri();

        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.getForObject(uri, User.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }
}
