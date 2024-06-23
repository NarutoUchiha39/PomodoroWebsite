package com.Authentication.Auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import com.Authentication.Auth.Entities.Roles;
import com.Authentication.Auth.Entities.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, String> {

    @Query("select t from UserInfo t join Roles r where t.email = :email")
     List<Roles> findAllByEmail(@Param("email")String email);

    Optional<UserInfo> findByEmail(String email);

}
