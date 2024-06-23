package com.Authentication.Auth.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Authentication.Auth.Entities.Token;
import com.Authentication.Auth.Entities.UserInfo;
import com.Authentication.Auth.classes.AuthenticationResponse;
import com.Authentication.Auth.repositories.TokenRepository;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserInfoService userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;


    public AuthenticationService(UserInfoService userRepository,
                                 PasswordEncoder passwordEncoder, JWTService jwtService,
                                 AuthenticationManager authenticationManager,
                                 TokenRepository tokenRepository)
    {
        this.userRepository = userRepository ;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }
    private void revokeAllTokenByUser(UserInfo user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getEmail());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    public AuthenticationResponse register(UserInfo request) {

        UserInfo check = userRepository.fetchUser(request.getEmail());
        if (check==null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(request.getUsername());
            userInfo.setEmail(request.getEmail());
            userInfo.setMobileNumber(request.getMobileNumber());
            userInfo.setPassword(passwordEncoder.encode(request.getPassword()));
            userInfo.setRoles(request.getRoles());

            UserInfo userInfo1 = userRepository.saveUser(userInfo);
            String token = jwtService.getJWTToken(userInfo1);

            saveUserToken(token, userInfo1);

            return new AuthenticationResponse(token, "User registration was successful :D");

        }
        return new AuthenticationResponse(null,"User Already exists :'D");

    }

    private void saveUserToken(String jwt, UserInfo user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUserInfo(user);
        tokenRepository.save(token);
    }

    public AuthenticationResponse authenticate(UserInfo request) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    ));

        UserInfo user = userRepository.fetchUser(request.getEmail());
        if(user != null) {
            String jwt = jwtService.getJWTToken(user);
            revokeAllTokenByUser(user);
            saveUserToken(jwt, user);
            return new AuthenticationResponse(jwt, "User login was successful :D");
        }

        return new AuthenticationResponse(null, "User login was unsuccessful :'D");



    }

}