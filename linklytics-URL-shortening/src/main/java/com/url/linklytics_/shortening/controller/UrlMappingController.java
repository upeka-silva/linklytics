package com.url.linklytics_.shortening.controller;
import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.model.User;
import com.url.linklytics_.shortening.service.UrlMappingService;
import com.url.linklytics_.shortening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlMappingController {

    @Autowired
    private UrlMappingService urlMappingService;
    @Autowired
    private UserService userService;

    //{key,value}={originalUrl,shorterUrl}
    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")   // this controller method is a authenticated method you cannot access this method without proper authentication
    public ResponseEntity<UrlMappingDto>creatShortUrl(@RequestBody Map<String,String>request, Principal principal){ //auto injected we dont need explicity say
         String originalUrl = request.get("originalUrl");
         User user= userService.findUserByUserName(principal.getName());
         UrlMappingDto urlMappingDto = urlMappingService.creatShortUrl(originalUrl, user);
         return ResponseEntity.ok(urlMappingDto);

    }



}
