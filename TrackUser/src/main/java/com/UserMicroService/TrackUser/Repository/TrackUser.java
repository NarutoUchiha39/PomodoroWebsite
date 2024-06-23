package com.UserMicroService.TrackUser.Repository;

import com.UserMicroService.TrackUser.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackUser extends JpaRepository<UserInfo,String> {
}
