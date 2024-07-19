package com.example.demo.services;

import com.example.demo.DTO.EntityDTOMapper;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.models.Product;
import com.example.demo.models.Seller;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(EntityDTOMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(EntityDTOMapper::toProductDTO)
                .orElse(null);
    }

    public List<ProductDTO> saveProduct(List<ProductDTO> productDTOs) {
        return productDTOs.stream()
                .map(this::saveOneProduct)
                .collect(Collectors.toList());
    }

    private ProductDTO saveOneProduct(ProductDTO productDTO) {
        if (productDTO.getSellerId() == null) {
            throw new IllegalArgumentException("Seller ID must not be null");
        }
        Seller seller = sellerRepository.findById(productDTO.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Seller not found with id: " + productDTO.getSellerId()));
        Product product = EntityDTOMapper.toProduct(productDTO);
        product.setSeller(seller);
        Product savedProduct = productRepository.save(product);
        return EntityDTOMapper.toProductDTO(savedProduct);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ProductDTO> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId).stream()
                .map(EntityDTOMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO buyProduct(Long id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        if (product.getStockQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock");
        }
        product.setStockQuantity(product.getStockQuantity() - quantity);
        Product updatedProduct = productRepository.save(product);
        return EntityDTOMapper.toProductDTO(updatedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        Product updatedProduct = productRepository.save(product);
        return EntityDTOMapper.toProductDTO(updatedProduct);
    }
}