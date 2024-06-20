package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.TrademarkDTO;
import org.springframework.http.ResponseEntity;

public interface TrademarkService {
    ResponseEntity<?> getAllTrademarks();

    ResponseEntity<?> saveTrademark(TrademarkDTO trademarkDTO);

    ResponseEntity<?> updateTrademark(TrademarkDTO trademarkDTO, int trademarkId);

    ResponseEntity<?> deleteTrademark(int trademarkId);

    boolean existsByTrademarkName(String name);
}
