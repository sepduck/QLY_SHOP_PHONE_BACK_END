package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.GroupSupplierDTO;
import org.springframework.http.ResponseEntity;

public interface GroupSupplierService {
    ResponseEntity<?> getAllGroupSupplier();

    ResponseEntity<?> saveGroupSupplier(GroupSupplierDTO groupSupplierDTO);

    ResponseEntity<?> updateGroupSupplier(GroupSupplierDTO groupSupplierDTO, int groupSupplierId);

    ResponseEntity<?> deleteGroupSupplier(int groupSupplierId);

    boolean existsByGroupSupplierName(String groupSupplierName);
}
