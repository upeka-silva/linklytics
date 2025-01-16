package com.url.linklytics_.shortening.controller;

import com.url.linklytics_.shortening.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/redirections")
public class RedirectController {
    @Autowired
    private UrlMappingService urlMappingService;

    @GetMapping("/shortUrl/{shortUrl}")
    public ResponseEntity<Void>redirect(@PathVariable String shortUrl){
        ResponseEntity<Void> originalUrl = urlMappingService.getOriginalUrl(shortUrl);
        return originalUrl;
    }

}
