package com.example.demo.DTO;

import java.util.List;

public class SellerDTO {
    private Long id;
    private String name;
    private String contactInfo;
    private String address;
    private List<Long> productIds;

    public SellerDTO() {}

    public SellerDTO(Long id, String name, String contactInfo, String address, List<Long> productIds) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.productIds = productIds;
    }

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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}