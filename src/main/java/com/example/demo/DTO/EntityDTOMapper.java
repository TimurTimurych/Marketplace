package com.example.demo.DTO;
import com.example.demo.models.Product;
import com.example.demo.models.Seller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EntityDTOMapper {

    public static SellerDTO toSellerDTO(Seller seller) {
        SellerDTO sellerDTO = new SellerDTO();

        sellerDTO.setId(seller.getId());
        sellerDTO.setName(seller.getName());
        sellerDTO.setContactInfo(seller.getContactInfo());
        sellerDTO.setAddress(seller.getAddress());

        List<Long> productIds = seller.getProducts() != null
                ? seller.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList())
                : Collections.emptyList();

        sellerDTO.setProductIds(productIds);

        return sellerDTO;
    }

    public static Seller toSeller(SellerDTO sellerDTO) {
        Seller seller = new Seller();
        seller.setId(sellerDTO.getId());
        seller.setName(sellerDTO.getName());
        seller.setContactInfo(sellerDTO.getContactInfo());
        seller.setAddress(sellerDTO.getAddress());
        return seller;
    }

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        if (product.getSeller() != null) {
            dto.setSellerId(product.getSeller().getId());
        }
        return dto;
    }

    public static Product toProduct(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        return product;
    }
}