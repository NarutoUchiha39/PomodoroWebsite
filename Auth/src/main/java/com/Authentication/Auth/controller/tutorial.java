package com.Authentication.Auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Authentication.Auth.Entities.UserInfo;
import com.Authentication.Auth.Services.AuthenticationService;
import com.Authentication.Auth.classes.AuthenticationResponse;

@RestController
public class tutorial {

    private final AuthenticationService authService;

    public tutorial(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserInfo request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserInfo request
    ) {
        System.out.println(request.getPassword());
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
