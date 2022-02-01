package com.mpiskorz.github.addon.service;

import com.mpiskorz.github.addon.model.Login;
import com.mpiskorz.github.addon.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void incrementLoginRequestCount(String loginString) {

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
