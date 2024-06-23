package com.Spring_IA_2.PomodoroWebsite.classes.UsersLogin;

import java.io.Serializable;

public class UserLogin implements Serializable {

    private String password;
    private String email;

    public UserLogin(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public UserLogin() {
    }

    public String getName() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
