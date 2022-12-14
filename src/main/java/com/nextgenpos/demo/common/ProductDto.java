package com.nextgenpos.demo.common;

import com.nextgenpos.demo.entities.Category;

import java.util.List;

public class ProductDto {
    private Long id;
    private String name;
    private Integer stock;
    private String description;
    private Integer price;
    private List<String> categoriesName;

    public ProductDto(Long id, String name, Integer stock, String description, Integer price, List<String> categoriesName) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.price = price;
        this.categoriesName = categoriesName;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getStock() {
        return stock;
    }
    public String getDescription() {
        return description;
    }
    public Integer getPrice() {
        return price;
    }

    public List<String> getCategoriesName() {
        return categoriesName;
    }
}
