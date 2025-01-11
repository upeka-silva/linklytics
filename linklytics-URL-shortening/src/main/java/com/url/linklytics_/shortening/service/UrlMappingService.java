package com.url.linklytics_.shortening.service;

import com.url.linklytics_.shortening.dtos.ClickEventDto;
import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UrlMappingService {

    UrlMappingDto creatShortUrl(String originalUrl, User user);

    List<UrlMappingDto> getUrlsByUserId(Long id);

    List< ClickEventDto> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end);



}
