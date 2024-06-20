package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dto.TrademarkDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Trademark;
import com.qlyshopphone_backend.repository.TrademarkRepository;
import com.qlyshopphone_backend.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrademarkServiceImpl extends BaseReponse implements TrademarkService {
    @Autowired
    private TrademarkRepository trademarkRepository;

    @Override
    public ResponseEntity<?> getAllTrademarks() {
        return getResponseEntity(trademarkRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveTrademark(TrademarkDTO trademarkDTO) {
        try {
            Trademark trademark = new Trademark();
            trademark.setTrademarkName(trademarkDTO.getTrademarkName());
            return getResponseEntity(trademarkRepository.save(trademark));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Trademark save failed");
        }
    }

    @Override
    public ResponseEntity<?> updateTrademark(TrademarkDTO trademarkDTO, int trademarkId) {
        Optional<Trademark> trademark = trademarkRepository.findById(trademarkId);
        if (trademark.isPresent()) {
            Trademark existingTrademark = trademark.get();
            existingTrademark.setTrademarkName(trademarkDTO.getTrademarkName());
            trademarkRepository.save(existingTrademark);
            return getResponseEntity("Trademark update successful");
        } else {
            return getResponseEntity("Trademark not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteTrademark(int trademarkId) {
        Optional<Trademark> trademark = trademarkRepository.findById(trademarkId);
        if (trademark.isPresent()) {
            trademarkRepository.deleteById(trademarkId);
            return getResponseEntity("Trademark delete successful");
        } else {
            return getResponseEntity("Trademark not found");
        }
    }

    @Override
    public boolean existsByTrademarkName(String name) {
        return trademarkRepository.existsByTrademarkName(name);
    }
}
