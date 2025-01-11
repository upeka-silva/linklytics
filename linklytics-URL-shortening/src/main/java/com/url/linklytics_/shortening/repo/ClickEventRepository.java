package com.url.linklytics_.shortening.repo;

import com.url.linklytics_.shortening.model.ClickEvent;
import com.url.linklytics_.shortening.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent,Long> {

    List<ClickEvent> findByUrlMappingAndCreatedDateBetween(UrlMapping mapping,// filter the ClickEvenets usinh mappinf and startDate and endDate
                                                           LocalDateTime startDate,
                                                           LocalDateTime endDate);

    List<ClickEvent> findByUrlMappingInAndCreatedDateBetween(List<UrlMapping> mapping,// filter the ClickEvenets usinh mappinf and startDate and endDate
                                                           LocalDateTime startDate,
                                                           LocalDateTime endDate);

}
