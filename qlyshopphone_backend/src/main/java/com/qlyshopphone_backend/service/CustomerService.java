package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.UsersDTO;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<?> getAllCustomers();

    ResponseEntity<?> saveCustomer(UsersDTO usersDTO);

    ResponseEntity<?> getCustomerById(int userId);

    ResponseEntity<?> deleteCustomerById(int userId);

    ResponseEntity<?> searchCustomerByName(String fullName);

    ResponseEntity<?> searchCustomerByEmail(String customerEmail);

    ResponseEntity<?> searchCustomerByPhone(String customerPhone);

    ResponseEntity<?> searchCustomerById(int userId);

    ResponseEntity<?> searchCustomerByAddress(String address);

    ResponseEntity<?> searchCustomerByActive(byte active);

    ResponseEntity<?> searchCustomerByGender(int number);
}
