package com.url.linklytics_.shortening.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "click_events")
public class ClickEvent {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "url_mapping_id")
    private UrlMapping urlMapping;


    public ClickEvent() {
    }

    public ClickEvent(Long id, LocalDateTime createdDate, UrlMapping urlMapping) {
        this.id = id;
        this.createdDate = createdDate;
        this.urlMapping = urlMapping;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public UrlMapping getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(UrlMapping urlMapping) {
        this.urlMapping = urlMapping;
    }
}
