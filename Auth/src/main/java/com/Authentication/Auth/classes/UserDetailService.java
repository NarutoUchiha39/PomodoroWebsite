package com.Authentication.Auth.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Authentication.Auth.Entities.UserInfo;
import com.Authentication.Auth.repositories.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    UserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(userRepository.findByEmail(email));
        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Doesnot Exists!!"));
    }
}
