package com.UserMicroService.TrackUser.Services;

import com.UserMicroService.TrackUser.Entities.UserInfo;
import com.UserMicroService.TrackUser.Repository.TrackUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TimeStudiesService {
    private TrackUser trackUser;
    public TimeStudiesService(TrackUser trackUser) {
        this.trackUser = trackUser;
    }

    public UserInfo getTimeStudied(String email){
        Optional<UserInfo> userInfo = trackUser.findById(email);
        System.out.println(userInfo);
        return userInfo.orElse(null);
    }

    public UserInfo updateTimeStudied(String email, Long timeStudied) {

        Optional<UserInfo> userInfo  = trackUser.findById(email);
        if (userInfo.isPresent()) {
            UserInfo user = userInfo.get();
            Long timeStudied_prev = user.getTime_studied();
            user.setTime_studied(timeStudied + timeStudied_prev);
            return trackUser.save(user);
        }

        return trackUser.save(new UserInfo(email, timeStudied));

    }
}
