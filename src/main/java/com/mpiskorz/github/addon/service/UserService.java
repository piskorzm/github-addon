package com.mpiskorz.github.addon.service;

import com.mpiskorz.github.addon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private GithubService githubService;
    private LoginService loginService;

    @Autowired
    public UserService(GithubService githubService, LoginService loginService) {
        this.githubService = githubService;
        this.loginService = loginService;
    }

    public UserService(GithubService githubService) {
        this.githubService = githubService;
    }

    public User getUser(String login) {
        User user = this.githubService.getUser(login);

        if (user != null) {
            this.loginService.incrementLoginRequestCount(login);
        }

        return user;
    }
}
