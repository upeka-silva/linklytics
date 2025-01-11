package com.url.linklytics_.shortening.controller;
import com.url.linklytics_.shortening.dtos.ClickEventDto;
import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.model.ClickEvent;
import com.url.linklytics_.shortening.model.User;
import com.url.linklytics_.shortening.service.UrlMappingService;
import com.url.linklytics_.shortening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
//    @PreAuthorize("hasRole('USER')")   // this controller method is a authenticated method you cannot access this method without proper authentication
    public ResponseEntity<UrlMappingDto>creatShortUrl(@RequestBody Map<String,String>request, Principal principal){ //auto injected we dont need explicity say
         String originalUrl = request.get("originalUrl");
         User user= userService.findUserByUserName(principal.getName());
         UrlMappingDto urlMappingDto = urlMappingService.creatShortUrl(originalUrl, user);
         return ResponseEntity.ok(urlMappingDto);
    }

    @GetMapping("/myUrls")
//    @PreAuthorize("hasRole('USER')")   // this controller method is a authenticated method you cannot access this method without proper authentication
    public ResponseEntity< List<UrlMappingDto>>getAllUserUrls(Principal principal){ //auto injected we dont need explicity say
        User user = userService.findUserByUserName(principal.getName());
        List<UrlMappingDto>urlMappingDtoList = urlMappingService.getUrlsByUserId(user.getId());
        return ResponseEntity.ok(urlMappingDtoList);
    }

    @GetMapping("/analytics/{shortUrl}")
//    @PreAuthorize("hasRole('USER')")   // this controller method is a authenticated method you cannot access this method without proper authentication
    public ResponseEntity<List<ClickEventDto>>getAllClickEvents(@PathVariable String shortUrl
            ,@RequestParam("startDate")String startDate
            ,@RequestParam("endDate")String  endDate){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate,dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(endDate,dateTimeFormatter);
        List<ClickEventDto> clickEventsByDateDtos = urlMappingService.getClickEventsByDate(shortUrl, start, end);
        return ResponseEntity.ok(clickEventsByDateDtos);

    }












}
