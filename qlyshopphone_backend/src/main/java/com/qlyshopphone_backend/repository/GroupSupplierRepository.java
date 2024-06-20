package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.GroupSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSupplierRepository extends JpaRepository<GroupSupplier, Integer> {
    boolean existsByGroupSupplierName(String groupSupplierName);
}
