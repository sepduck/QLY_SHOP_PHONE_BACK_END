package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.UnitDTO;
import org.springframework.http.ResponseEntity;

public interface UnitService {
    ResponseEntity<?> getAllUnits();

    ResponseEntity<?> saveUnit(UnitDTO unitDTO);

    ResponseEntity<?> updateUnit(UnitDTO unitDTO, int unitId);

    ResponseEntity<?> deleteUnit(int unitId);

    boolean existsByUnitName(String name);
}
