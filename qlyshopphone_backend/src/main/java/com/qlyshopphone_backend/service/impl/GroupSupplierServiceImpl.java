package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dto.GroupSupplierDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.GroupSupplier;
import com.qlyshopphone_backend.repository.GroupSupplierRepository;
import com.qlyshopphone_backend.service.GroupSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupSupplierServiceImpl extends BaseReponse implements GroupSupplierService {
    @Autowired
    private GroupSupplierRepository groupSupplierRepository;

    @Override
    public ResponseEntity<?> getAllGroupSupplier() {
        return getResponseEntity(groupSupplierRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveGroupSupplier(GroupSupplierDTO groupSupplierDTO) {
        try {
            GroupSupplier groupSupplier = new GroupSupplier();
            groupSupplier.setGroupSupplierName(groupSupplierDTO.getGroupSupplierName());
            groupSupplier.setNote(groupSupplierDTO.getNote());
            groupSupplierRepository.save(groupSupplier);
            return getResponseEntity("GroupSupplier saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateGroupSupplier(GroupSupplierDTO groupSupplierDTO, int groupSupplierId) {
        Optional<GroupSupplier> groupSupplier = groupSupplierRepository.findById(groupSupplierId);
        if (groupSupplier.isPresent()) {
            GroupSupplier existingGroupSupplier = groupSupplier.get();
            existingGroupSupplier.setGroupSupplierName(groupSupplierDTO.getGroupSupplierName());
            existingGroupSupplier.setNote(groupSupplierDTO.getNote());
            groupSupplierRepository.save(existingGroupSupplier);
            return getResponseEntity("GroupSupplier updated successfully");
        } else {
            return getResponseEntity("GroupSupplier not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteGroupSupplier(int groupSupplierId) {
        Optional<GroupSupplier> groupSupplier = groupSupplierRepository.findById(groupSupplierId);
        if (groupSupplier.isPresent()) {
            groupSupplierRepository.deleteById(groupSupplierId);
            return getResponseEntity("GroupSupplier deleted successfully");
        } else {
            return getResponseEntity("GroupSupplier not found");
        }
    }

    @Override
    public boolean existsByGroupSupplierName(String groupSupplierName) {
        return groupSupplierRepository.existsByGroupSupplierName(groupSupplierName);
    }
}
