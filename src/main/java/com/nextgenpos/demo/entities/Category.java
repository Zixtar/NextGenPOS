package com.nextgenpos.demo.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Category {
    private Long id;
    private String name;
    private List<Product> products;
    public void setId(Long id) {
        this.id = id;
    }
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    @Basic
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(
            name="PERSISTENCE_CATEGORY_PRODUCT",
            joinColumns=
            @JoinColumn(name="CATEGORY_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")
    )
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
