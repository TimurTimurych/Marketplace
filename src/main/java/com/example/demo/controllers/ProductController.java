package com.example.demo.controllers;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ProductDTO>> getProductsBySellerId(@PathVariable Long sellerId) {
        List<ProductDTO> products = productService.getProductsBySellerId(sellerId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<List<ProductDTO>> addProduct(@RequestBody List<ProductDTO> products) {
        List<ProductDTO> savedProducts = productService.saveProduct(products);
        return ResponseEntity.ok(savedProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDetails) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/buy")
    public ResponseEntity<ProductDTO> buyProduct(@PathVariable Long id, @RequestParam int quantity) {
        try {
            ProductDTO updatedProduct = productService.buyProduct(id, quantity);
            return ResponseEntity.ok(updatedProduct);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}