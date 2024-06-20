package com.qlyshopphone_backend.controller.rest;

import com.qlyshopphone_backend.model.Users;
import com.qlyshopphone_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class RestLoginController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/info")
    public ResponseEntity<Users> getAccountInfo(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        Users user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }
}
