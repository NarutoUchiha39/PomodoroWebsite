package com.UserMicroService.TrackUser.Controller;

import com.UserMicroService.TrackUser.Classes.Response;
import com.UserMicroService.TrackUser.Classes.User;
import com.UserMicroService.TrackUser.Services.TimeStudiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api")
public class UserInfo {
    TimeStudiesService timeStudiesService;
    UserInfo(TimeStudiesService timeStudiesService){
        this.timeStudiesService = timeStudiesService;
    }
    @RequestMapping(value = "/getTimeStudied",method = RequestMethod.POST)
    public ResponseEntity<com.UserMicroService.TrackUser.Entities.UserInfo> getTimeStudied(@RequestBody User user){
        com.UserMicroService.TrackUser.Entities.UserInfo userInfo = timeStudiesService.getTimeStudied(user.getEmail());
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @RequestMapping(value = "/updateTimer",method = RequestMethod.POST)
    public ResponseEntity<Response> updateTimer(@RequestBody User user){

        System.out.println(user.getEmail()+" "+user.getTime());

        com.UserMicroService.TrackUser.Entities.UserInfo userInfo = timeStudiesService.updateTimeStudied(user.getEmail(),user.getTime());
        if(userInfo != null){
            return new ResponseEntity<>(new Response("Updated Successfully"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Response(""),HttpStatus.valueOf(400));
    }
}
