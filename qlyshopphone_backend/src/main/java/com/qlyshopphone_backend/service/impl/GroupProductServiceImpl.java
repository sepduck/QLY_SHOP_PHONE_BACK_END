package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dto.GroupProductDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.GroupProduct;
import com.qlyshopphone_backend.repository.GroupProductRepository;
import com.qlyshopphone_backend.service.GroupProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupProductServiceImpl extends BaseReponse implements GroupProductService {
    @Autowired
    private GroupProductRepository groupProductRepository;

    @Override
    public ResponseEntity<?> getAllGroupProduct() {
        return getResponseEntity(groupProductRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveGroupProduct(GroupProductDTO groupProductDTO) {
        try {
            GroupProduct groupProduct = new GroupProduct();
            groupProduct.setGroupProductName(groupProductDTO.getGroupProductName());
            groupProductRepository.save(groupProduct);
            return getResponseEntity("GroupProduct saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> updateGroupProduct(GroupProductDTO groupProductDTO, int groupProductId) {
        Optional<GroupProduct> groupProductOptional = groupProductRepository.findById(groupProductId);
        if (groupProductOptional.isPresent()) {
            GroupProduct groupProduct = groupProductOptional.get();
            groupProduct.setGroupProductName(groupProductDTO.getGroupProductName());
            groupProductRepository.save(groupProduct);
            return getResponseEntity("GroupProduct updated successfully");
        } else {
            return getResponseEntity("GroupProduct not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteGroupProduct(int groupProductId) {
        Optional<GroupProduct> groupProductOptional = groupProductRepository.findById(groupProductId);
        if (groupProductOptional.isPresent()) {
            groupProductRepository.deleteById(groupProductId);
            return getResponseEntity("GroupProduct deleted successfully");
        } else {
            return getResponseEntity("GroupProduct not found");
        }


    }

    @Override
    public boolean existsByGroupProductName(String groupProductName) {
        return groupProductRepository.existsByGroupProductName(groupProductName);
    }
}
