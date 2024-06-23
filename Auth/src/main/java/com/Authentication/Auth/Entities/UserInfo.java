package com.Authentication.Auth.Entities;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users",indexes = @Index(columnList ="email" ,unique = true,name = "email_index"))
public class UserInfo implements UserDetails {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "roles")
    @ManyToMany(targetEntity = Roles.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name ="users_id" ,referencedColumnName ="email" ),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_name"))
    private List<Roles> roles;
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(roles);
        String [] role = new String[roles.size()];
        int index  =0;
        for (Roles roles1: roles){
            role[index] = roles1.getRole_name();
            index+=1;
        }
        return Arrays.stream(role).map(SimpleGrantedAuthority::new).toList();
    }

    public String getPassword() {
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private String mobileNumber;


}
