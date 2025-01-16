package com.url.linklytics_.shortening.service.impl;
import com.url.linklytics_.shortening.dtos.ClickEventDto;
import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.mappers.UrlMappingMapper;
import com.url.linklytics_.shortening.model.ClickEvent;
import com.url.linklytics_.shortening.model.UrlMapping;
import com.url.linklytics_.shortening.model.User;
import com.url.linklytics_.shortening.repo.ClickEventRepository;
import com.url.linklytics_.shortening.repo.UrlMappingRepository;
import com.url.linklytics_.shortening.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UrlMappingServiceImpl implements UrlMappingService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Autowired
    private UrlMappingMapper urlMappingMapper;

    @Autowired
    private ClickEventRepository clickEventRepository;

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

    @Override
    public List<UrlMappingDto> getUrlsByUserId(Long id) {
        List<UrlMappingDto>urlMappingDtoList= new ArrayList<>();
        urlMappingRepository.findAllByUserId(id).forEach((e)->urlMappingDtoList.add(
                urlMappingMapper.toDto(e)
        ));
        return urlMappingDtoList;
    }

    @Override
    public List<ClickEventDto> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping selectedUrlMapping = urlMappingRepository.findByShorterUrl(shortUrl);
        if ( selectedUrlMapping != null ){
             return  clickEventRepository.
                     findByUrlMappingAndCreatedDateBetween(selectedUrlMapping,start,end)
                     .stream().
                     collect(Collectors.groupingBy(clickEvent -> clickEvent.             //grouping the data by dates
                             getCreatedDate().toLocalDate(),
                             Collectors.counting())).entrySet().stream().map(localDateLongEntry ->{
                                 ClickEventDto clickEventDto = new ClickEventDto();
                                 clickEventDto.setClickDate(localDateLongEntry.getKey());
                                 clickEventDto.setCount(localDateLongEntry.getValue());
                                 return clickEventDto;

                     } ).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Map<LocalDate, Long> getTotalClicksByUserAndDateShortUrl(User user, LocalDate start, LocalDate end) {

        List<UrlMapping> urlMappings = urlMappingRepository.findAllByUserId(user.getId());
        List<ClickEvent> clickEvents = clickEventRepository.
                findByUrlMappingInAndCreatedDateBetween(urlMappings, start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        return clickEvents.stream().collect(Collectors.groupingBy(clickEvent -> clickEvent.getCreatedDate().toLocalDate(),Collectors.counting()));
    }


    @Override
    public ResponseEntity<Void> getOriginalUrl(String shortUrl) {
        UrlMapping selectedUrlMapping = urlMappingRepository.findByShorterUrl(shortUrl);
        if (selectedUrlMapping != null){
            selectedUrlMapping.setClickCount(selectedUrlMapping.getClickCount()+1);
            urlMappingRepository.save(selectedUrlMapping);
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setCreatedDate(LocalDateTime.now());
            clickEvent.setUrlMapping(selectedUrlMapping);
            clickEventRepository.save(clickEvent);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location",selectedUrlMapping.getOriginalUrl());
            return ResponseEntity.status(302).headers(httpHeaders).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private String generateShortUrl() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);
        for(int i = 0 ; i<8 ; i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }



}
