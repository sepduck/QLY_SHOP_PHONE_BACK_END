package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.UsersDTO;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface EmployeeService {
    ResponseEntity<?> getAllEmployees();

    ResponseEntity<?> getEmployeeById(int id);

    ResponseEntity<?> saveEmployee(UsersDTO usersDTO);

    void saveEmployeeRoles(int userId) throws SQLException;

    ResponseEntity<?> deleteEmployeeById(int id);

    ResponseEntity<?> searchEmployeeById(int id);

    ResponseEntity<?> searchEmployeeByName(String employeeName);

    ResponseEntity<?> searchEmployeeByPhoneNumber(String phoneNumber);

    ResponseEntity<?> searchEmployeeByActive(int number);
}
