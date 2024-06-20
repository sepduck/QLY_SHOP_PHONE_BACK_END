package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.PropertiesDTO;
import org.springframework.http.ResponseEntity;

public interface PropertiesService {
    ResponseEntity<?> getAllProperties();

    ResponseEntity<?> saveProperties(PropertiesDTO propertiesDTO);

    ResponseEntity<?> updateProperties(PropertiesDTO propertiesDTO, int propertiesId);

    ResponseEntity<?> deleteProperties(int propertiesId);

    boolean existsByPropertiesName(String name);
}
