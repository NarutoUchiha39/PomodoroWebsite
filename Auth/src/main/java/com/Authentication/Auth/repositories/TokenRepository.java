package com.Authentication.Auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Authentication.Auth.Entities.Token;

import java.util.List;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("""
    select t from Token t inner join UserInfo u on t.userInfo.email = u.email
    where t.userInfo.email = :email and t.loggedOut = false
    """)
    List<Token> findAllTokensByUser(String  email);

    Optional<Token> findByToken(String token);

}
