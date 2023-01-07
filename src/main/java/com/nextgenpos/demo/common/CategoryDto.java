package com.nextgenpos.demo.common;

import com.nextgenpos.demo.entities.Product;

import java.util.List;

public class CategoryDto {
    private Long id;
    private String name;
    private List<String> products;

    public CategoryDto(Long id, String name, List<String> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getProducts() {
        return products;
    }
}
