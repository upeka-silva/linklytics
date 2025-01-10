package com.url.linklytics_.shortening.service;

import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.model.User;

public interface UrlMappingService {

    UrlMappingDto creatShortUrl(String originalUrl, User user);
}
