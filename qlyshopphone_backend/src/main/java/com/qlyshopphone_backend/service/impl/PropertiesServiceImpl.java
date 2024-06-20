package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dto.PropertiesDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Properties;
import com.qlyshopphone_backend.repository.PropertiesRepository;
import com.qlyshopphone_backend.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PropertiesServiceImpl extends BaseReponse implements PropertiesService {
    @Autowired
    private PropertiesRepository propertiesRepository;

    @Override
    public ResponseEntity<?> getAllProperties() {
        return getResponseEntity(propertiesRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveProperties(PropertiesDTO propertiesDTO) {
        try {
            Properties properties = new Properties();
            properties.setPropertiesName(propertiesDTO.getPropertiesName());
            propertiesRepository.save(properties);
            return getResponseEntity("Properties saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateProperties(PropertiesDTO propertiesDTO, int propertiesId) {
        Optional<Properties> properties = propertiesRepository.findById(propertiesId);
        if (properties.isPresent()) {
            Properties existingProperties = properties.get();
            existingProperties.setPropertiesName(propertiesDTO.getPropertiesName());
            propertiesRepository.save(existingProperties);
            return getResponseEntity("Properties updated successfully");
        } else {
            return getResponseEntity("Properties not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteProperties(int propertiesId) {
        Optional<Properties> properties = propertiesRepository.findById(propertiesId);
        if (properties.isPresent()) {
            propertiesRepository.deleteById(propertiesId);
            return getResponseEntity("Properties deleted successfully");
        } else {
            return getResponseEntity("Properties not found");
        }
    }

    @Override
    public boolean existsByPropertiesName(String name) {
        return propertiesRepository.existsByPropertiesName(name);
    }
}
