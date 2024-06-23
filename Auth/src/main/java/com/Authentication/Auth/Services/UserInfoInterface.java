package com.Authentication.Auth.Services;

import java.util.List;

import com.Authentication.Auth.Entities.Roles;
import com.Authentication.Auth.Entities.UserInfo;

public interface UserInfoInterface {
    UserInfo saveUser(UserInfo userInfo);
    List<UserInfo>ListUserInfo();

    UserInfo fetchUser(String username);

    UserInfo updateUser(UserInfo userInfo,String username);

    void deleteUser(String username);

    List<Roles> fetchRoles(String username);

}
