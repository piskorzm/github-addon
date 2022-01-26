package com.mpiskorz.github.addon.service;

import com.mpiskorz.github.addon.model.Login;
import com.mpiskorz.github.addon.model.User;
import com.mpiskorz.github.addon.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final GithubService githubService;

    @Autowired
    private LoginRepository loginRepository;

    public UserService(GithubService githubService) {
        this.githubService = githubService;
    }

    public User getUser(String login) {
        User user = this.githubService.getUser(login);

        if (user != null) {
            this.incrementLoginRequestCount(login);
        }

        return user;
    }

    private void incrementLoginRequestCount(String loginString) {

        Optional<Login> loginOptional = this.loginRepository.findById(loginString);
        Login login;

        if (loginOptional.isPresent()) {
            login = loginOptional.get();
            login.incrementRequestCount();
        } else {
            login = new Login(loginString);
        }

        this.loginRepository.save(login);

    }
}
