package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
    boolean existsByUnitName(String name);
}
