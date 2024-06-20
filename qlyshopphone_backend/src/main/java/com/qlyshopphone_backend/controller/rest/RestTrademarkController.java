package com.qlyshopphone_backend.controller.rest;

import com.github.javafaker.Faker;
import com.qlyshopphone_backend.dto.SupplierDTO;
import com.qlyshopphone_backend.dto.TrademarkDTO;
import com.qlyshopphone_backend.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestTrademarkController {
    @Autowired
    private TrademarkService trademarkService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/trademark")
    public ResponseEntity<?> getAllTrademark() {
        return trademarkService.getAllTrademarks();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PostMapping("/trademark")
    public ResponseEntity<?> saveTrademark(@RequestBody TrademarkDTO trademarkDTO) {
        return trademarkService.saveTrademark(trademarkDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")

    @PutMapping("/trademark/{id}")
    public ResponseEntity<?> updateTrademark(@RequestBody TrademarkDTO trademarkDTO,
                                             @PathVariable int id) {
        return trademarkService.updateTrademark(trademarkDTO, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/trademark/{id}")
    public ResponseEntity<?> deleteTrademark(@PathVariable int id) {
        return trademarkService.deleteTrademark(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/trademark/generateFake")
    public ResponseEntity<String> generateFake() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String trademark = faker.name().fullName();
            if (trademarkService.existsByTrademarkName(trademark)) {
                continue;
            }
            TrademarkDTO trademarkDTO = TrademarkDTO.builder()
                    .trademarkName(trademark)
                    .build();
            try {
                trademarkService.saveTrademark(trademarkDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.ok("Fake trademark generated");
    }
}
