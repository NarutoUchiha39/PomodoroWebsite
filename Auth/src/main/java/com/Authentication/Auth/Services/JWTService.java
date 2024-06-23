package com.Authentication.Auth.Services;


import com.Authentication.Auth.Entities.Roles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import com.Authentication.Auth.Entities.UserInfo;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {
     private final String SecretKey = "96E0AFB1EF27B847BD338CABF3E20CB6BD53F3CE2ED0F6AE6AC3AC8C5C16E711";

     public String extractEmail(String token){
            return extractClaim(token,"email");
     }

     private boolean isTokenExpired(String token){
         System.out.println("Hi"+extractExpiration(token));
         return extractExpiration(token) > (System.currentTimeMillis());
     }
     public boolean isValid(String token, UserInfo userInfo){
         System.out.println(extractEmail(token));
         System.out.println(!isTokenExpired(token));
         return userInfo.getEmail().equals(extractEmail(token)) && !isTokenExpired(token);
     }

     private Long extractExpiration(String token){

         return extractClaim(token,"expiration");
     }
     public <T> T extractClaim(String token,String claimName){
         Claims claims = ExtractAllClaims(token);
         if(claimName.equals("expiration")){
             return (T) claims.get("exp");
         }

         else {
             return (T) claims.get("email");
         }


     }
     private Claims ExtractAllClaims(String token){
                return Jwts.
                        parser()
                        .verifyWith(SignWithKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
     }
     public String getJWTToken(UserInfo userInfo){

         Map<String,String>map = new HashMap<>();

         map.put("userName",userInfo.getUsername());
         map.put("mobileNumber",userInfo.getMobileNumber());
         map.put("email",userInfo.getEmail());

         StringBuilder Roles_String =  new StringBuilder();
         for (Roles roles: userInfo.getRoles()){
             Roles_String.append(roles.getRole_name()).append(";");
         }

         map.put("Roles",Roles_String.toString());

         String Token = Jwts.builder()
                 .claims(map)
                 .issuedAt(new Date(System.currentTimeMillis()))
                 .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                 .signWith(SignWithKey())
                 .compact();

         return Token;
     }

     public javax.crypto.SecretKey SignWithKey(){
         byte[]keyBytes = Decoders.BASE64.decode(SecretKey);
         return Keys.hmacShaKeyFor(keyBytes);
     }
}
