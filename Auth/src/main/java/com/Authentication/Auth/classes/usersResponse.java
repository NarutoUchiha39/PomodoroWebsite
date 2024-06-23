package com.Authentication.Auth.classes;

import java.util.List;

import com.Authentication.Auth.Entities.Roles;

public class usersResponse {
    private String username;

    public String getUsername() {
        return username;
    }


    public usersResponse(String username, String email, String mobileNumber,List<Roles> roles) {
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.roles = roles;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    private String email;
    private String mobileNumber;

    private List<Roles> roles;
}
