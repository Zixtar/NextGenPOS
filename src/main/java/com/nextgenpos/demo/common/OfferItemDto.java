package com.nextgenpos.demo.common;

import com.nextgenpos.demo.entities.OfferBundle;
import com.nextgenpos.demo.entities.Product;

import java.util.Date;

public class OfferItemDto {

    private Integer id;

    private Product product;

    private OfferBundle bundleId;

    private Float newPrice;

    private Date beginningDate;

    private Date endDate;

    public OfferItemDto(Integer id, Product product, OfferBundle bundleId, Float newPrice, Date beginningDate, Date endDate) {
        this.id = id;
        this.product = product;
        this.bundleId = bundleId;
        this.newPrice = newPrice;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }


    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public OfferBundle getBundleId() {
        return bundleId;
    }

    public Float getNewPrice() {
        return newPrice;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
