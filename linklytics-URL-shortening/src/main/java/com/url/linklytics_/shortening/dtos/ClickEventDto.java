package com.url.linklytics_.shortening.dtos;

import java.time.LocalDate;

public class ClickEventDto {

    private LocalDate clickDate;
    private long count;

    public ClickEventDto() {
    }
    public ClickEventDto(LocalDate clickDate, long count) {
        this.clickDate = clickDate;
        this.count = count;
    }

    public LocalDate getClickDate() {
        return clickDate;
    }

    public void setClickDate(LocalDate clickDate) {
        this.clickDate = clickDate;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
