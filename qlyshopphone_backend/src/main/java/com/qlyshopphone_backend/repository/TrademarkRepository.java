package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrademarkRepository extends JpaRepository<Trademark, Integer> {
    boolean existsByTrademarkName(String name);
}
