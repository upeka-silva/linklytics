package com.url.linklytics_.shortening.security.jwt;

import com.url.linklytics_.shortening.service.UserDetailsImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;


public class JwtUtils {

       @Value("${jwt-secret}")
       private String jwtSecret;
       @Value("${jwt-expirationDate}")
       private int expirationDate;
        //Authorization --> http header --> bearer <token>
      public String extractJwtToken(HttpServletRequest httpRequest){ //helper method for help extact the token form the http header
          String bearerToken =  httpRequest.getHeader("Authorization");
          if(bearerToken != null && bearerToken.startsWith("Bearer")){
              return bearerToken.substring(7);
          }
          return  null;
      }
      public String generateToken(UserDetailsImpl userDetails){
          String userName = userDetails.getUsername();
          String roles = userDetails.
                  getAuthorities().
                  stream().map(authorities ->authorities.getAuthority()).collect(Collectors.joining(","));
          return Jwts.builder().subject(userName).claim("roles",roles)
                  .issuedAt(new Date()).expiration(new Date((new Date().getTime() +expirationDate))).signWith(key()).compact();
      }

      public String getUserNameFormJwtToken(String token){
          return Jwts.parser().verifyWith((SecretKey)key()).build().parseSignedClaims(token).getPayload().getSubject();
      }

      private Key key(){
         return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
      }

      public boolean validateToken(String token){
          try {
              Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token);
              return  true;
          } catch (JwtException e) {
              throw new RuntimeException(e);
          } catch (IllegalArgumentException e) {
              throw new RuntimeException(e);
          }catch (Exception e) {
              throw new RuntimeException(e);
          }
      }



}
