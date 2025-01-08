package com.url.linklytics_.shortening.service.impl;

import com.url.linklytics_.shortening.model.User;
import com.url.linklytics_.shortening.repo.UserRepository;
import com.url.linklytics_.shortening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User userRegister(User user) {
        if(user!= null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
           return userRepository.save(user);
        }
        throw new RuntimeException("Invalid User!");
    }





}
