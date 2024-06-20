package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query(value = "SELECT location_id AS id, location_name\n" +
            "FROM location", nativeQuery = true)
    List<Map<String, Object>> getAllLocations();

    boolean existsByLocationName(String locationName);
}
