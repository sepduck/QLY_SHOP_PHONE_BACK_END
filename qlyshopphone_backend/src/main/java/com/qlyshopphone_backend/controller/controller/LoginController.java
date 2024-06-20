package com.qlyshopphone_backend.controller.controller;

import com.qlyshopphone_backend.dto.UsersDTO;
import com.qlyshopphone_backend.model.Users;
import com.qlyshopphone_backend.service.AuthenticationService;
import com.qlyshopphone_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        return authenticationService.login(users);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersDTO usersDTO) {
        usersDTO.setEmployee(0);
        usersDTO.setActive(1);
        usersDTO.setRoleId(3);
        return authenticationService.register(usersDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam int id) {
        return userService.findByUserId(id);
    }
}
