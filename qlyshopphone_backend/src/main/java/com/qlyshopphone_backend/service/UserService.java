package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.UsersDTO;
import com.qlyshopphone_backend.model.Users;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface UserService {
    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> saveUser(Users user);

    ResponseEntity<?> update(UsersDTO usersDTO);

    ResponseEntity<?> findByUserId(int id);

    Users findByUsername(String username);

    ResponseEntity<?> deleteUser(int id);
}
