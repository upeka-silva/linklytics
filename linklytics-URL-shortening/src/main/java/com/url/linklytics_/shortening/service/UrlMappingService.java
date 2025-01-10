package com.url.linklytics_.shortening.service;

import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.model.User;

import java.util.List;

public interface UrlMappingService {

    UrlMappingDto creatShortUrl(String originalUrl, User user);

    List<UrlMappingDto> getUrlsByUserId(Long id);
}
