package com.url.linklytics_.shortening.service;

import com.url.linklytics_.shortening.dtos.ClickEventDto;
import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.model.User;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UrlMappingService {



    UrlMappingDto creatShortUrl(String originalUrl, User user);

    List<UrlMappingDto> getUrlsByUserId(Long id);

    List< ClickEventDto> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end);

    Map<LocalDate,Long> getTotalClicksByUserAndDateShortUrl(User user, LocalDate start, LocalDate end);


    ResponseEntity<Void> getOriginalUrl(String shortUrl);





}
