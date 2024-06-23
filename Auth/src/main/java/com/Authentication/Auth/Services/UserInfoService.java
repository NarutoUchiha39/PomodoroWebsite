package com.Authentication.Auth.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Authentication.Auth.Entities.Roles;
import com.Authentication.Auth.Entities.UserInfo;
import com.Authentication.Auth.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserInfoInterface{


    private final UserRepository userRepository;
    @Autowired
    UserInfoService(UserRepository infoInterface){
            this.userRepository = infoInterface;
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
          return userRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> ListUserInfo() {
        return (List<UserInfo>)userRepository.findAll();
    }

    @Override
    public UserInfo fetchUser(String username) {
        Optional<UserInfo>userInfo = userRepository.findById(username);
        return userInfo.orElse(null);
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo, String username) {
        return null;
    }

    @Override
    public void deleteUser(String username) {

        userRepository.deleteById(username);

    }

    @Override
    public List<Roles> fetchRoles(String email) {
        return userRepository.findAllByEmail(email);
    }
}
