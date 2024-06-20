package com.qlyshopphone_backend.controller.rest;

import com.github.javafaker.Faker;
import com.qlyshopphone_backend.dto.SupplierDTO;
import com.qlyshopphone_backend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestSupplierController {
    @Autowired
    private SupplierService supplierService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/supplier")
    public ResponseEntity<?> getSupplier() {
        return supplierService.getSupplier();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/suppliers")
    public ResponseEntity<?> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PostMapping("/supplier")
    public ResponseEntity<?> saveSuppliers(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.saveSuppliers(supplierDTO);
    }
    // Delete ----------------------------------------------------------------------------------------------------------
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/supplier/{supplierId}")
    public ResponseEntity<?> deleteSuppliers(@PathVariable int supplierId) {
        return supplierService.deleteSuppliers(supplierId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/suppliers/findById/{supplierId}")
    public ResponseEntity<?> findBySuppliersId(@PathVariable int supplierId) {
        return supplierService.findBySuppliersId(supplierId);
    }

    // Search by phone number ------------------------------------------------------------------------------------------
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/suppliers/search-phone/{phoneNumber}")
    public ResponseEntity<?> searchAllByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return supplierService.searchByPhoneNumber(phoneNumber);
    }

    //  Search by Tax Code ---------------------------------------------------------------------------------------------
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/suppliers/search-tax-code/{taxCode}")
    public ResponseEntity<?> searchAllByTaxCode(@PathVariable("taxCode") String taxCode) {
        return supplierService.searchByTaxCode(taxCode);
    }

    //  Search by supplier name ----------------------------------------------------------------------------------------
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/suppliers/search-name/{supplierName}")
    public ResponseEntity<?> searchSupplierName(@PathVariable("supplierName") String supplierName) {
        return supplierService.searchBySupplierName(supplierName);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/suppliers/search-group-supplier/{groupSupplierId}")
    public ResponseEntity<?> searchByGroupSupplier(@PathVariable int groupSupplierId){
        return supplierService.searchByGroupSupplier(groupSupplierId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/suppliers/search-supplier-active/{number}")
    public ResponseEntity<?> searchActive(@PathVariable int number){
        return supplierService.searchNoActive(number);
    }

    //  Fake data ------------------------------------------------------------------------------------------------------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/suppliers/generateFake")
    public ResponseEntity<String> generateFake() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String supplier = faker.name().fullName();
            String taxCode = String.valueOf(faker.number().numberBetween(10, 10));
            if (supplierService.existsBySupplierNameAndTaxCode(supplier, taxCode)) {
                continue;
            }
            SupplierDTO supplierDTO = SupplierDTO.builder()
                    .supplierName(supplier)
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .address(faker.address().fullAddress())
                    .email(faker.internet().emailAddress())
                    .company(faker.company().name())
                    .taxCode(taxCode)
                    .groupSupplierId(faker.number().numberBetween(1, 100))
                    .productId(faker.number().numberBetween(1, 17))
                    .build();
            try {
                supplierService.saveSuppliers(supplierDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.ok("Fake orders generated");
    }
}
