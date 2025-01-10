package com.url.linklytics_.shortening.service.impl;

import com.url.linklytics_.shortening.dtos.LogInRequest;
import com.url.linklytics_.shortening.model.User;
import com.url.linklytics_.shortening.repo.UserRepository;
import com.url.linklytics_.shortening.security.jwt.JwtAuthenticationResponse;
import com.url.linklytics_.shortening.security.jwt.JwtUtils;
import com.url.linklytics_.shortening.service.UserDetailsImpl;
import com.url.linklytics_.shortening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public User userRegister(User user) {
        if(user!= null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
           return userRepository.save(user);
        }
        throw new RuntimeException("Invalid User!");
    }

    @Override
    public JwtAuthenticationResponse authenticateUser(LogInRequest logInRequest) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logInRequest.getUserName(),logInRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails1 = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails1);// we have to use here type casting
        return new JwtAuthenticationResponse(token);
    }

    @Override
    public User findUserByUserName(String name) {
      return   userRepository.findByUserName(name).orElseThrow(()-> new UsernameNotFoundException("This user not found!"+name));
    }


}
