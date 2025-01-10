package com.url.linklytics_.shortening.dtos;


import lombok.Data;

import java.time.LocalDateTime;


public class UrlMappingDto {

    private String originalUrl;
    private String shortUrl;
    private Long id;
    private int clickCount;
    private LocalDateTime createdDate;
    private String userName;

    public UrlMappingDto() {
    }

    public UrlMappingDto(String originalUrl, String shortUrl, Long id, int clickCount, LocalDateTime createdDate, String userName) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.id = id;
        this.clickCount = clickCount;
        this.createdDate = createdDate;
        this.userName = userName;
    }


    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
