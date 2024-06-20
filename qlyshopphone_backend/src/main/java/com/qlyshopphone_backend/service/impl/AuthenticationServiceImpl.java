package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dto.UsersDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Roles;
import com.qlyshopphone_backend.model.Users;
import com.qlyshopphone_backend.repository.RoleRepository;
import com.qlyshopphone_backend.repository.UserRepository;
import com.qlyshopphone_backend.service.AuthenticationService;
import com.qlyshopphone_backend.service.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationServiceImpl extends BaseReponse implements AuthenticationService {
    @Autowired
    private JwtProvider provider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private Set<String> invalidatedTokens = new HashSet<>();

    @Override
    public ResponseEntity<?> login(Users users) {
       try {
           Authentication authentication =
                   authenticationManager
                           .authenticate(new UsernamePasswordAuthenticationToken
                                   (users.getUsername(), users.getPassword()));
           SecurityContextHolder.getContext().setAuthentication(authentication);
           String token = provider.generateToken(authentication);
           return getResponseEntity(token);
       }catch (Exception e) {
           return getResponseEntity(e.getMessage());
       }
    }

    @Override
    public ResponseEntity<?> register(UsersDTO usersDTO) {
        Users users = new Users();
        users.setUsername(usersDTO.getUsername());
        users.setPassword(usersDTO.getPassword());
        users.setPhoneNumber(usersDTO.getPhoneNumber());
        users.setStartDay(LocalDate.now());
        users.setIdCard(usersDTO.getIdCard());
        users.setGenderId(usersDTO.getGenderId());
        users.setFacebook(usersDTO.getFacebook());
        users.setEmail(usersDTO.getEmail());
        users.setAddress(usersDTO.getAddress());
        users.setActive(usersDTO.getActive());
        users.setEmployee(usersDTO.getEmployee());
        users.setFullname(usersDTO.getFullName());
        users.setBirthday(usersDTO.getBirthday());

        Roles roles = roleRepository.findById(usersDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        users.setRoles(List.of(roles));
        return getResponseEntity(userRepository.save(users));
    }
    @Override
    public void invalidateToken(String token) {
        invalidatedTokens.add(token);
    }
    @Override
    public boolean isTokenInvalidated(String token) {
        return invalidatedTokens.contains(token);
    }
}
