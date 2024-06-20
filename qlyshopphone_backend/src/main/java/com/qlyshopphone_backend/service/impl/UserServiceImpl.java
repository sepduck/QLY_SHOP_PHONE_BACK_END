package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.JDBC.DatabaseUtil;
import com.qlyshopphone_backend.dao.UserDAO;
import com.qlyshopphone_backend.dto.UsersDTO;
import com.qlyshopphone_backend.exceptions.DataNotFoundException;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Roles;
import com.qlyshopphone_backend.model.Users;
import com.qlyshopphone_backend.repository.UserRepository;
import com.qlyshopphone_backend.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserServiceImpl extends BaseReponse implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> getAllUsers() {
        return getResponseEntity(userRepository.getAllUsers());
    }

    @Override
    public ResponseEntity<?> saveUser(Users user) {
        try{
            userRepository.save(user);
            return getResponseEntity(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> update(UsersDTO usersDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> findByUserId(int id) {
        return getResponseEntity(userRepository.findById(id));
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return getResponseEntity("User deleted successfully");
        } else {
            return getResponseEntity("User not found");
        }
    }
}
