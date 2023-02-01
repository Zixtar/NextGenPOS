package com.nextgenpos.demo.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class OfferItem {

    private Long id;

    private Product product;

    private OfferBundle bundleId;
    private Float newPrice;
    private Date beginningDate;
    private Date endDate;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Float newPrice) {
        this.newPrice = newPrice;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    public OfferBundle getBundleId() {
        return bundleId;
    }

    public void setBundleId(OfferBundle bundleId) {
        this.bundleId = bundleId;
    }

    @Override
    public String toString() {
        return "OfferItem{" +
                "id=" + id +
                ", product=" + product +
                ", bundleId=" + bundleId +
                ", newPrice=" + newPrice +
                ", beginningDate=" + beginningDate +
                ", endDate=" + endDate +
                '}';
    }
}
