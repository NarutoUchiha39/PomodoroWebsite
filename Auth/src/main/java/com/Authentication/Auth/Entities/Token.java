package com.Authentication.Auth.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token",columnDefinition = "TEXT")
    private String token;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @ManyToOne(targetEntity =UserInfo.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public Token(Long id, String token, boolean loggedOut, UserInfo userInfo) {
        this.id = id;
        this.token = token;
        this.loggedOut = loggedOut;
        this.userInfo = userInfo;
    }

    public Token() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }


    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }


}
