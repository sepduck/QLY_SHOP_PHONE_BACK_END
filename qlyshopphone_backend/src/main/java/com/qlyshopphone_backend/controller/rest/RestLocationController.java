package com.qlyshopphone_backend.controller.rest;

import com.github.javafaker.Faker;
import com.qlyshopphone_backend.dto.GroupSupplierDTO;
import com.qlyshopphone_backend.dto.LocationDTO;
import com.qlyshopphone_backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping()
public class RestLocationController {
    @Autowired
    private LocationService locationService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/location")
    public ResponseEntity<?> getAllLocation() {
        return locationService.getAllLocation();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PostMapping("/location")
    public ResponseEntity<?> saveLocation(@RequestBody LocationDTO locationDTO) {
        return locationService.saveLocation(locationDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @PutMapping("/location/{id}")
    public ResponseEntity<?> updateLocation(@RequestBody LocationDTO locationDTO,
                                            @PathVariable int id) {
        return locationService.updateLocation(locationDTO, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/location/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable int id) {
        return locationService.deleteLocation(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/group-location/generateFake")
    public ResponseEntity<String> generateFake() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String location = faker.leagueOfLegends().location();
            if (locationService.existsByLocationName(location)){
                continue;
            }
            LocationDTO locationDTO = LocationDTO.builder()
                    .locationName(location)
                    .build();
            try {
                locationService.saveLocation(locationDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.ok("Fake locations generated");
    }
}
