package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.repository.GenderRepository;
import com.qlyshopphone_backend.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl extends BaseReponse implements GenderService {
    @Autowired
    private GenderRepository genderRepository;

    @Override
    public ResponseEntity<?> getAllGender() {
        return getResponseEntity(genderRepository.findAll());
    }
}
