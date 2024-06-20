package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Roles;
import com.qlyshopphone_backend.repository.RoleRepository;
import com.qlyshopphone_backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseReponse implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> getAllRoles() {
        return getResponseEntity(roleRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveRole(Roles roles) {
        return getResponseEntity(roleRepository.save(roles));
    }

    @Override
    public ResponseEntity<?> findByRoleId(int id) {
        return getResponseEntity(roleRepository.findById(id));
    }
}
