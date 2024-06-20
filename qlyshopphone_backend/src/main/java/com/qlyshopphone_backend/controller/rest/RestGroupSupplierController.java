package com.qlyshopphone_backend.controller.rest;

import com.github.javafaker.Faker;
import com.qlyshopphone_backend.dto.GroupSupplierDTO;
import com.qlyshopphone_backend.service.GroupSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestGroupSupplierController {
    @Autowired
    private GroupSupplierService groupSupplierService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/group-supplier")
    public ResponseEntity<?> getAllGroupSupplier() {
        return groupSupplierService.getAllGroupSupplier();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PostMapping("/group-supplier")
    public ResponseEntity<?> saveGroupSupplier(@RequestBody GroupSupplierDTO groupSupplierDTO) {
        return groupSupplierService.saveGroupSupplier(groupSupplierDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PutMapping("/group-supplier/{id}")
    public ResponseEntity<?> updateGroupSupplier(@RequestBody GroupSupplierDTO groupSupplierDTO,
                                                    @PathVariable int id) {
        return groupSupplierService.updateGroupSupplier(groupSupplierDTO,id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/group-supplier/{id}")
    public ResponseEntity<?> deleteGroupSupplier(@PathVariable int id) {
        return groupSupplierService.deleteGroupSupplier(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/group-supplier/generateFaker")
    public ResponseEntity<String> generateFake() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String supplier = faker.name().fullName();
            if (groupSupplierService.existsByGroupSupplierName(supplier)){
                continue;
            }
            GroupSupplierDTO groupSupplierDTO = GroupSupplierDTO.builder()
                    .groupSupplierName(supplier)
                    .note(faker.toString())
                    .build();
            try {
                groupSupplierService.saveGroupSupplier(groupSupplierDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.ok("Fake group suppliers generated");
    }
}
