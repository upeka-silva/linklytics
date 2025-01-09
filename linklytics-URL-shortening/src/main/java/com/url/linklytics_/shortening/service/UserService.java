package com.url.linklytics_.shortening.service;


import com.url.linklytics_.shortening.dtos.LogInRequest;
import com.url.linklytics_.shortening.model.User;
import com.url.linklytics_.shortening.security.jwt.JwtAuthenticationResponse;

public interface UserService {
    User userRegister(User user);
    JwtAuthenticationResponse authenticateUser(LogInRequest logInRequest);
}
