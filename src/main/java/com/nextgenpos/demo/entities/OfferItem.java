package com.nextgenpos.demo.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;

import java.util.Date;

@Entity
public class OfferItem {
    @Id
    @GeneratedValue
    private Integer id;

    private Product product;

    private OfferBundle bundleId;
    private Float newPrice;
    private Date beginningDate;
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}
