package com.mpiskorz.github.addon.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Login")
public class Login {

    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "REQUEST_COUNT")
    private Integer requestCount;

    public Login() {

    }

    public Login(String login) {
        this.login = login;
        this.requestCount = 1;
    }

    public void incrementRequestCount() {
        requestCount++;
    }
}
