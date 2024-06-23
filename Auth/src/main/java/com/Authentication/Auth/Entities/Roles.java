package com.Authentication.Auth.Entities;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Roles {

   @Id
    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Column(name = "role_name")
    private String role_name;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<UserInfo> userInfos;

    public  String toString(){
        return getRole_name();
    }
}
