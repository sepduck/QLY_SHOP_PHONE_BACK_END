package com.qlyshopphone_backend.controller.rest;

import com.github.javafaker.Faker;
import com.qlyshopphone_backend.dto.GroupProductDTO;
import com.qlyshopphone_backend.service.GroupProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestGroupProductController {
    @Autowired
    private GroupProductService groupProductService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/group-product")
    public ResponseEntity<?> getAllGroupProduct() {
        return groupProductService.getAllGroupProduct();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PostMapping("/group-product")
    public ResponseEntity<?> saveGroupProduct(@RequestBody GroupProductDTO groupProductDTO) {
        return groupProductService.saveGroupProduct(groupProductDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PutMapping("/group-product/{id}")
    public ResponseEntity<?> updateGroupProduct(@RequestBody GroupProductDTO groupProductDTO,
                                                @PathVariable int id) {
        return groupProductService.updateGroupProduct(groupProductDTO, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/group-product/{id}")
    public ResponseEntity<?> deleteGroupProduct(@PathVariable int id) {
        return groupProductService.deleteGroupProduct(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> generateFake() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String product = faker.name().fullName();
            if (groupProductService.existsByGroupProductName(product)){
                continue;
            }
            GroupProductDTO groupProductDTO = GroupProductDTO.builder()
                    .groupProductName(product)
                    .build();
            try {
                groupProductService.saveGroupProduct(groupProductDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.ok("Fake group products generated");
    }
}
