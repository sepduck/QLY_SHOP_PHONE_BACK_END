package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.UsersDTO;
import com.qlyshopphone_backend.model.Users;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> login(Users users);

    ResponseEntity<?> register(UsersDTO usersDTO);

    void invalidateToken(String token);

    boolean isTokenInvalidated(String token);
}
