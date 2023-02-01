package com.nextgenpos.demo.common;

public class SaleItemCreatorDto {

    private Long id;

    private String ProductName;

    private Integer Quantity;

    private Integer Price;

    private Long PhotoId;

    public Long getPhotoId() {
        return PhotoId;
    }

    public void setPhotoId(Long photoId) {
        PhotoId = photoId;
    }

    public SaleItemCreatorDto(Long id, String productName, Integer quantity, Integer price) {
        this.id = id;
        ProductName = productName;
        Quantity = quantity;
        Price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }
}
