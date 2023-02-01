package com.nextgenpos.demo.entities;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
public class Sale {
    @Id
    private String id = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SALE_ID")
    private Collection<SaleItem> saleItems;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CASHIER_ID", nullable = false)
    private User Cashier;

    private String State;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        if(State == null)
            return "";
        return State;
    }

    public void setState(String status) {
        this.State = status;
    }
}
