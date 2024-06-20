package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dto.UnitDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Unit;
import com.qlyshopphone_backend.repository.UnitRepository;
import com.qlyshopphone_backend.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitServiceImpl extends BaseReponse implements UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public ResponseEntity<?> getAllUnits() {
        return getResponseEntity(unitRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveUnit(UnitDTO unitDTO) {
        try {
            Unit unit = new Unit();
            unit.setUnitName(unitDTO.getUnitName());
            unitRepository.save(unit);
            return getResponseEntity("Unit saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateUnit(UnitDTO unitDTO, int unitId) {
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        if (unitOptional.isPresent()) {
            Unit existingUnit = unitOptional.get();
            existingUnit.setUnitName(unitDTO.getUnitName());
            unitRepository.save(existingUnit);
            return getResponseEntity("Unit updated successfully");
        } else {
            return getResponseEntity("Unit not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteUnit(int unitId) {
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        if (unitOptional.isPresent()) {
            unitRepository.deleteById(unitId);
            return getResponseEntity("Unit deleted successfully");
        } else {
            return getResponseEntity("Unit not found");
        }
    }

    @Override
    public boolean existsByUnitName(String name) {
        return unitRepository.existsByUnitName(name);
    }
}
