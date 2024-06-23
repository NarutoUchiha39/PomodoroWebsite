package com.Authentication.Auth.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Authentication.Auth.Entities.Roles;

public interface RoleRepositor extends JpaRepository<Roles,Long>{
    
}
