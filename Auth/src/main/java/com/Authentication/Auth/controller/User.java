package com.Authentication.Auth.controller;
import com.Authentication.Auth.Entities.UserInfo;
import com.Authentication.Auth.TemplateClasses.Music;
import com.Authentication.Auth.classes.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Status Code: 400 -> User Exists error
 * */
@RestController
@RequestMapping(value = "/api")
public class User {

    @RequestMapping (value = "/getSongsList",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Music>> getSongsList(){
        RestTemplate getSongsList = new RestTemplate();
        ResponseEntity<ArrayList> musicArrayList = getSongsList.getForEntity("http://localhost:8081/api/getSongsList", ArrayList.class);

        if (musicArrayList.getStatusCode() == HttpStatus.OK){
            ArrayList<Music>music = (ArrayList<Music>) musicArrayList.getBody();
            return new ResponseEntity<>(music, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/downloadSong/{songName}",method = RequestMethod.GET)
    public ResponseEntity<String> downloadSong(@PathVariable("songName") String songName){
        System.out.println(songName);
        RestTemplate getSongsList = new RestTemplate();
        ResponseEntity<String> musicArrayList = getSongsList.getForEntity("http://localhost:8081/api/downloadSong/"+songName, String.class);
        if(musicArrayList.getStatusCode().equals(HttpStatus.valueOf(200))){
            System.out.println(musicArrayList.getBody());
            return new ResponseEntity<>(musicArrayList.getBody().toString(),HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(musicArrayList.getBody().toString(),HttpStatus.valueOf(musicArrayList.getStatusCode().toString()));

    }

    @RequestMapping(value = "/getTimeStudied",method = RequestMethod.GET)
    public ResponseEntity<com.Authentication.Auth.classes.User> getTimeStudied(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currentPrincipalName = (UserInfo)authentication.getPrincipal();
        System.out.println("HI "+currentPrincipalName.getEmail());
        RestTemplate getTimeStudied = new RestTemplate();
        ResponseEntity<com.Authentication.Auth.classes.User>response = getTimeStudied.postForEntity("http://localhost:8085/getTimeStudied",new com.Authentication.Auth.classes.User(currentPrincipalName.getEmail(),null,null),com.Authentication.Auth.classes.User.class);

        if (response.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/updateTimer/{id}",method = RequestMethod.GET)
    public ResponseEntity<Response> updateTimer(@PathVariable("id") String time){
        Long timeStudied = Long.parseLong(time);
        RestTemplate updateTimer = new RestTemplate();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currentPrincipalName =(UserInfo)authentication.getPrincipal();
        ResponseEntity<Response>response = updateTimer.postForEntity("http://localhost:8085/updateTimer",new com.Authentication.Auth.classes.User(currentPrincipalName.getEmail(),timeStudied,null),Response.class);

        if (response.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
