package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.GroupProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupProductRepository extends JpaRepository<GroupProduct, Integer> {
    boolean existsByGroupProductName(String groupProductName);
}
