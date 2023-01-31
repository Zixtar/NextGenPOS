package com.nextgenpos.demo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    private Long id;
    private String name;
    private Integer stock;
    private String description;
    private Integer price;

    private List<Category> categories;
    private List<ProductPhoto> photos;

    private List<User> wishlistedByUsers;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @ManyToMany(mappedBy="products")
    public List<Category> getCategories() {
        if(categories == null)
            setCategories(new ArrayList<>());
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<ProductPhoto> getPhotos() {
        if(photos == null)
            setPhotos(new ArrayList<>());
        return photos;
    }

    public void setPhotos(List<ProductPhoto> photos) {
        this.photos = photos;
    }
    @ManyToMany(mappedBy = "wishlistProducts")
    public List<User> getWishlistedByUsers() {
        return wishlistedByUsers;
    }

    public void setWishlistedByUsers(List<User> wishlistedByUsers) {
        this.wishlistedByUsers = wishlistedByUsers;
    }
}
