package com.qlyshopphone_backend.controller.rest;

import com.github.javafaker.Faker;
import com.qlyshopphone_backend.dto.PropertiesDTO;
import com.qlyshopphone_backend.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestPropertiesController {
    @Autowired
    private PropertiesService propertiesService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/properties")
    public ResponseEntity<?> getAllProperties(){
        return propertiesService.getAllProperties();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PostMapping("/properties")
    public ResponseEntity<?> saveProperties(@RequestBody PropertiesDTO propertiesDTO){
        return propertiesService.saveProperties(propertiesDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PutMapping("/properties/{id}")
    public ResponseEntity<?> updateProperties(@RequestBody PropertiesDTO propertiesDTO,
                                              @PathVariable int id){
        return propertiesService.updateProperties(propertiesDTO, id);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/properties/{id}")
    public ResponseEntity<?> deleteProperties(@PathVariable int id){
        return propertiesService.deleteProperties(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/properties/generateFake")
    public ResponseEntity<String> generateFake() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String properties = faker.name().fullName();
            if (propertiesService.existsByPropertiesName(properties)){
                continue;
            }
            PropertiesDTO propertiesDTO = PropertiesDTO.builder()
                    .propertiesName(properties)
                    .build();
            try {
                propertiesService.saveProperties(propertiesDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.ok("Fake properties generated");
    }
}
