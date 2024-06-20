package com.qlyshopphone_backend.controller.rest;

import com.qlyshopphone_backend.dto.UsersDTO;
import com.qlyshopphone_backend.model.Users;
import com.qlyshopphone_backend.service.AuthenticationService;
import com.qlyshopphone_backend.service.GenderService;
import com.qlyshopphone_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private GenderService genderService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/gender")
    public ResponseEntity<?> getAllGender() {
        return genderService.getAllGender();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/info")
    public ResponseEntity<Users> getAccountInfo(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        Users user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody UsersDTO usersDTO) {
        return userService.update(usersDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @PostMapping("/log-out")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thiếu hoặc sai định dạng Authorization header");
            }

            String token = authorizationHeader.replace("Bearer ", "");
            authenticationService.invalidateToken(token);
            return ResponseEntity.ok("Đăng xuất thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra trong quá trình đăng xuất");
        }
    }

}
