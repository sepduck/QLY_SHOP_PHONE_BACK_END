package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.LocationDTO;
import org.springframework.http.ResponseEntity;

public interface LocationService {
    ResponseEntity<?> getAllLocation();

    ResponseEntity<?> saveLocation(LocationDTO locationDTO);

    ResponseEntity<?> updateLocation(LocationDTO locationDTO, int locationId);

    ResponseEntity<?> deleteLocation(int locationId);

    boolean existsByLocationName(String locationName);
}
