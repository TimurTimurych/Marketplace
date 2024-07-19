package com.example.demo.services;

import com.example.demo.DTO.EntityDTOMapper;
import com.example.demo.DTO.SellerDTO;
import com.example.demo.models.Seller;
import com.example.demo.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public List<SellerDTO> getAllSellers() {
        return sellerRepository.findAll().stream()
                .map(EntityDTOMapper::toSellerDTO)
                .collect(Collectors.toList());
    }

    public SellerDTO getSellerById(Long id) {
        return sellerRepository.findById(id)
                .map(EntityDTOMapper::toSellerDTO)
                .orElse(null);
    }

    public List<SellerDTO> saveSellers(List<SellerDTO> sellerDTOs) {
        List<Seller> sellers = sellerDTOs.stream()
                .map(EntityDTOMapper::toSeller)
                .collect(Collectors.toList());

        List<Seller> savedSellers = sellerRepository.saveAll(sellers);

        return savedSellers.stream()
                .map(EntityDTOMapper::toSellerDTO)
                .collect(Collectors.toList());
    }

    public SellerDTO updateSeller(Long id, SellerDTO sellerDetails) {
        return sellerRepository.findById(id)
                .map(existingSeller -> {
                    updateSellerEntity(existingSeller, sellerDetails);
                    return EntityDTOMapper.toSellerDTO(sellerRepository.save(existingSeller));
                })
                .orElse(null);
    }

    private void updateSellerEntity(Seller existingSeller, SellerDTO sellerDetails) {
        existingSeller.setName(sellerDetails.getName());
        existingSeller.setContactInfo(sellerDetails.getContactInfo());
        existingSeller.setAddress(sellerDetails.getAddress());
    }

    public boolean deleteSeller(Long id) {
        if (sellerRepository.existsById(id)) {
            sellerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
