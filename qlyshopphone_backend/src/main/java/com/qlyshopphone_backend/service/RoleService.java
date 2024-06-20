package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.model.Roles;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<?> getAllRoles();

    ResponseEntity<?> saveRole(Roles roles);

    ResponseEntity<?> findByRoleId(int id);


}
