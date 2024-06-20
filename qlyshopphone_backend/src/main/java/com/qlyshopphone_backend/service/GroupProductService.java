package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.GroupProductDTO;
import org.springframework.http.ResponseEntity;

public interface GroupProductService {
    ResponseEntity<?> getAllGroupProduct();

    ResponseEntity<?> saveGroupProduct(GroupProductDTO groupProductDTO);

    ResponseEntity<?> updateGroupProduct(GroupProductDTO groupProductDTO, int groupProductId);

    ResponseEntity<?> deleteGroupProduct(int groupProductId);

    boolean existsByGroupProductName(String groupProductName);
}
