package com.url.linklytics_.shortening.mappers;
import com.url.linklytics_.shortening.dtos.UrlMappingDto;
import com.url.linklytics_.shortening.model.UrlMapping;

import java.util.List;

public interface UrlMappingMapper {

    UrlMappingDto toDto(UrlMapping urlMapping);
//    UrlMapping toEntity (UrlMappingDto dto);
//    List<UrlMappingDto> toDtoList(UrlMapping urlMapping);


}
