package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.SupplierDTO;
import org.springframework.http.ResponseEntity;

public interface SupplierService {
    ResponseEntity<?> getAllSuppliers();

    ResponseEntity<?> saveSuppliers(SupplierDTO supplierDTO);

    ResponseEntity<?> deleteSuppliers(int supplierId);

    ResponseEntity<?> findBySuppliersId(int supplierId);

    ResponseEntity<?> searchByPhoneNumber(String phoneNumber);

    ResponseEntity<?> searchByTaxCode(String taxCode);

    ResponseEntity<?> searchBySupplierName(String supplierName);

    ResponseEntity<?> searchByGroupSupplier(int groupSupplierId);

    ResponseEntity<?> getSupplier();

    ResponseEntity<?> searchNoActive(int number);

    boolean existsBySupplierNameAndTaxCode(String supplierName, String taxCode);
}
