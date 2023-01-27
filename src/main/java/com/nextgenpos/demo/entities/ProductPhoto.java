package com.nextgenpos.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductPhoto {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
