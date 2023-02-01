package com.nextgenpos.demo.common;

import com.nextgenpos.demo.entities.CarouselPhoto;
import com.nextgenpos.demo.entities.OfferItem;

import java.util.Collection;

public class OfferBundleDto {

    private Long id;
    private String name;
    private Collection<OfferItem> offerItemList;

    private CarouselPhoto carouselOfferPhoto;

    public OfferBundleDto(Long id, String name, Collection<OfferItem> offerItemList, CarouselPhoto carouselOfferPhoto) {
        this.id = id;
        this.name = name;
        this.offerItemList = offerItemList;
        this.carouselOfferPhoto = carouselOfferPhoto;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<OfferItem> getOfferItemList() {
        return offerItemList;
    }

    public CarouselPhoto getCarouselOfferPhoto() {
        return carouselOfferPhoto;
    }
}
