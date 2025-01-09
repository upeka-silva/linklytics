package com.url.linklytics_.shortening.controller;
import com.url.linklytics_.shortening.dtos.LogInRequest;
import com.url.linklytics_.shortening.dtos.RegisterRequest;
import com.url.linklytics_.shortening.model.User;
import com.url.linklytics_.shortening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private  UserService userService;

     @PostMapping("/public/login")
     public ResponseEntity<?>loginUser(@RequestBody LogInRequest longInRequest){
        return ResponseEntity.ok(userService.authenticateUser(longInRequest));
    }

    @PostMapping("/public/register")
    public ResponseEntity<?>userRegister( @RequestBody RegisterRequest registerRequest){
       User user = new User();
       user.setUserName(registerRequest.getUserName());
       user.setEmail(registerRequest.getEmail());
       user.setPassword(registerRequest.getPassword());
       user.setRole("USER_ROLE");   // hard coded for our own convenience
        userService.userRegister(user);
       return ResponseEntity.ok("user registered successfully!");

    }



}
