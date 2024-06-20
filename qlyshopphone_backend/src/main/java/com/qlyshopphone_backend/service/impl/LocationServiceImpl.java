package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dto.LocationDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Location;
import com.qlyshopphone_backend.repository.LocationRepository;
import com.qlyshopphone_backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl extends BaseReponse implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public ResponseEntity<?> getAllLocation() {
        return getResponseEntity(locationRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveLocation(LocationDTO locationDTO) {
        try {
            Location location = new Location();
            location.setLocationName(locationDTO.getLocationName());
            locationRepository.save(location);
            return getResponseEntity("Location saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateLocation(LocationDTO locationDTO, int locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isPresent()) {
            Location existingLocation = location.get();
            existingLocation.setLocationName(locationDTO.getLocationName());
            locationRepository.save(existingLocation);
            return getResponseEntity("Location updated successfully");
        } else {
            return getResponseEntity("Location not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteLocation(int locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isPresent()) {
            locationRepository.deleteById(locationId);
            return getResponseEntity("Location deleted successfully");
        } else {
            return getResponseEntity("Location not found");
        }
    }

    @Override
    public boolean existsByLocationName(String locationName) {
        return locationRepository.existsByLocationName(locationName);
    }
}
