package com.nextgenpos.demo.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
public class Sale {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SALE_ID")
    private Collection<SaleItem> saleItems;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CASHIER_ID", nullable = false)
    private User Cashier;


    public Collection<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(Collection<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCashier() {
        return Cashier;
    }

    public void setCashier(User cashier) {
        Cashier = cashier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
