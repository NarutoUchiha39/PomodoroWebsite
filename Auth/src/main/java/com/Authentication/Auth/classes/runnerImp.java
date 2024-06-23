package com.Authentication.Auth.classes;

import java.util.ArrayList;
import java.util.List;
import com.Authentication.Auth.Entities.Roles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Authentication.Auth.repositories.RoleRepositor;

@Component
class runnerImp implements CommandLineRunner{

    RoleRepositor repositor;
    runnerImp(RoleRepositor repositor){
        this.repositor = repositor;
    }

    @Override
    public void run(String... args) throws Exception {
        Roles role_admin = new Roles();
        role_admin.setRole_name("ADMIN");
        Roles role_admin1 = repositor.saveAndFlush(role_admin);
        System.out.println(role_admin1.getRole_name());

        Roles role_user = new Roles();
        role_user.setRole_name("USER");
        Roles roles = repositor.saveAndFlush(role_user);
        System.out.println(role_admin1.getRole_name());
        System.out.println(roles.getRole_name());


        System.out.println("hi");
    }

    
}