package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Integer> {
    boolean existsByPropertiesName(String name);
}
