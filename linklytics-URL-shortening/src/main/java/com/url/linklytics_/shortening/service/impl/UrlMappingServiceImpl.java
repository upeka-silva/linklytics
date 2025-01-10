package com.url.linklytics_.shortening.service.impl;
import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.mappers.UrlMappingMapper;
import com.url.linklytics_.shortening.model.UrlMapping;
import com.url.linklytics_.shortening.model.User;
import com.url.linklytics_.shortening.repo.UrlMappingRepository;
import com.url.linklytics_.shortening.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;


@Service
public class UrlMappingServiceImpl implements UrlMappingService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Autowired
    private UrlMappingMapper urlMappingMapper;

    @Override
    public UrlMappingDto creatShortUrl(String originalUrl, User user) {

        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setCreatedDate(LocalDateTime.now());
        urlMapping.setUser(user);
        urlMapping.setShorterUrl(shortUrl);
        urlMappingRepository.save(urlMapping);
        return urlMappingMapper.toDto(urlMapping);

    }

    private String generateShortUrl() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);
        for(int i =0; i<8 ; i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }



}
