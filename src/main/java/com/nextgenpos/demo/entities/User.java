package com.nextgenpos.demo.entities;

import jakarta.persistence.*;


import java.util.ArrayList;

import java.util.List;

@Entity
public class User {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String telNr;
    private String email;
    private String mbti;
    private Address address;


    private List<ErrorReport> errorReports;

    private List<Product> wishlistProducts;

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<ErrorReport> getErrorReports() {
        if (errorReports == null) {
            setErrorReports(new ArrayList<>());
        }
        return errorReports;
    }

    public void setErrorReports(List<ErrorReport> errorReports) {
        this.errorReports = errorReports;


    }
    @ManyToMany
    @JoinTable(name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "ID"))
    public List<Product> getWishlistProducts() {
        return wishlistProducts;
    }

    public void setWishlistProducts(List<Product> wishlistProducts) {
        this.wishlistProducts = wishlistProducts;
    }
}
