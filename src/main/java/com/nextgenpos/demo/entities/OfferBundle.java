package com.nextgenpos.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.util.Collection;


@Entity
public class OfferBundle {

    private Long id;
    private String name;
    private Collection<OfferItem> offerItemList;

    private CarouselPhoto carouselOfferPhoto;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "bundleId")
    public Collection<OfferItem> getOfferItemList() {
        return offerItemList;
    }

    public void setOfferItemList(Collection<OfferItem> offerItemList) {
        this.offerItemList = offerItemList;
    }

    @OneToOne(mappedBy = "offerBundle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public CarouselPhoto getCarouselOfferPhoto(){
        return carouselOfferPhoto;
    }

    public void setCarouselOfferPhoto(CarouselPhoto photo){
        this.carouselOfferPhoto=photo;
    }

}
