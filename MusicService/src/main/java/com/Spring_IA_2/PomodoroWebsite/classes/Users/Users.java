package com.Spring_IA_2.PomodoroWebsite.classes.Users;

import java.io.Serializable;

public class Users implements Serializable {

    private String username;
    private String email;
    private String role;

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Users(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
