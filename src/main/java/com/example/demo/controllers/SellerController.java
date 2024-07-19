package com.example.demo.controllers;

import com.example.demo.DTO.SellerDTO;
import com.example.demo.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping
    public List<SellerDTO> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> getSellerById(@PathVariable Long id) {
        SellerDTO seller = sellerService.getSellerById(id);
        return seller != null ? ResponseEntity.ok(seller) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<List<SellerDTO>> addSellers(@RequestBody List<SellerDTO> sellers) {
        List<SellerDTO> savedSellers = sellerService.saveSellers(sellers);
        return ResponseEntity.ok(savedSellers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerDTO> updateSeller(@PathVariable Long id, @RequestBody SellerDTO sellerDetails) {
        SellerDTO updatedSeller = sellerService.updateSeller(id, sellerDetails);
        return updatedSeller != null ? ResponseEntity.ok(updatedSeller) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        boolean deleted = sellerService.deleteSeller(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}