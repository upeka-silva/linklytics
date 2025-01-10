package com.url.linklytics_.shortening.mappers.impl;

import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.mappers.UrlMappingMapper;
import com.url.linklytics_.shortening.model.UrlMapping;
import org.springframework.stereotype.Service;


@Service
public class UrlMappingMapperImpl implements UrlMappingMapper {

    @Override
    public UrlMappingDto toDto(UrlMapping urlMapping) {
        UrlMappingDto urlMappingDto = new UrlMappingDto();
        urlMappingDto.setUserName(urlMapping.getUser().getUserName());
        urlMappingDto.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDto.setShortUrl(urlMapping.getShorterUrl());
        urlMappingDto.setId(urlMapping.getId());
        urlMappingDto.setClickCount(urlMappingDto.getClickCount());
        return urlMappingDto;
    }

}
