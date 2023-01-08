package com.nextgenpos.demo.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Returned {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "RETURN_ID")
    private Collection<SaleItem> returnedItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALE_ID")
    private Sale sale;


    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Collection<SaleItem> getReturnedItems() {
        return returnedItems;
    }

    public void setReturnedItems(Collection<SaleItem> returnedItems) {
        this.returnedItems = returnedItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
