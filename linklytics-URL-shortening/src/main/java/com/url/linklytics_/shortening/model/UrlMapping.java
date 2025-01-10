package com.url.linklytics_.shortening.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shorterUrl;
    private int clickCount = 0;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "urlMapping")
    private List<ClickEvent> clickEvents;


    public UrlMapping() {
    }

    public UrlMapping(Long id, String originalUrl, String shorterUrl, int clickCount, LocalDateTime createdDate,
                      User user, List<ClickEvent> clickEvents) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shorterUrl = shorterUrl;
        this.clickCount = clickCount;
        this.createdDate = createdDate;
        this.user = user;
        this.clickEvents = clickEvents;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShorterUrl() {
        return shorterUrl;
    }

    public void setShorterUrl(String shorterUrl) {
        this.shorterUrl = shorterUrl;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ClickEvent> getClickEvents() {
        return clickEvents;
    }

    public void setClickEvents(List<ClickEvent> clickEvents) {
        this.clickEvents = clickEvents;
    }
}
