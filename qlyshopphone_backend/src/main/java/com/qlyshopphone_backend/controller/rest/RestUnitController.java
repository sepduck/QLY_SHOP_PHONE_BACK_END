package com.qlyshopphone_backend.controller.rest;

import com.github.javafaker.Faker;
import com.qlyshopphone_backend.dto.SupplierDTO;
import com.qlyshopphone_backend.dto.UnitDTO;
import com.qlyshopphone_backend.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestUnitController {

    @Autowired
    private UnitService unitService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/unit")
    public ResponseEntity<?> getAllUnits(){
        return unitService.getAllUnits();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PostMapping("/unit")
    public ResponseEntity<?> saveUnits(@RequestBody UnitDTO unitDTO){
        return unitService.saveUnit(unitDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PutMapping("/unit/{id}")
    public ResponseEntity<?> updateUnits(@RequestBody UnitDTO unitDTO,
                                         @PathVariable int id){
        return unitService.updateUnit(unitDTO,id);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/unit/{id}")
    public ResponseEntity<?> deleteUnits(@PathVariable int id){
        return unitService.deleteUnit(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/unit/generateFake")
    public ResponseEntity<String> generateFake() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String unit = faker.name().fullName();
            if (unitService.existsByUnitName(unit)) {
                continue;
            }
            UnitDTO unitDTO = UnitDTO.builder()
                    .unitName(unit)
                    .build();
            try {
                unitService.saveUnit(unitDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.ok("Fake units generated");
    }
}
