package com.nextgenpos.demo.common;

import java.time.LocalDate;
import java.util.Date;

public class ErrorReportDto {
    Long id;
    String description;
    Boolean state;
    LocalDate date;
    String username;

    public ErrorReportDto(Long id, String description, Boolean state, LocalDate date,String username) {
        this.id = id;
        this.description = description;
        this.state = state;
        this.date = date;
        this.username=username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
