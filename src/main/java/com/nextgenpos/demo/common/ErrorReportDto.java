package com.nextgenpos.demo.common;

import java.time.LocalDate;
import java.util.Date;

public class ErrorReportDto {
    Long id;
    String description;
    Boolean state;
    LocalDate date;

    public ErrorReportDto(Long id, String description, Boolean state, LocalDate date) {
        this.id = id;
        this.description = description;
        this.state = state;
        this.date = date;
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
}
