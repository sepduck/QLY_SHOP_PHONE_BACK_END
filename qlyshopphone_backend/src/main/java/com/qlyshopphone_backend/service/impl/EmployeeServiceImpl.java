package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dao.EmployeeDAO;
import com.qlyshopphone_backend.dao.UserDAO;
import com.qlyshopphone_backend.dto.UsersDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.model.Roles;
import com.qlyshopphone_backend.model.Users;
import com.qlyshopphone_backend.repository.EmployeeRepository;
import com.qlyshopphone_backend.repository.RoleRepository;
import com.qlyshopphone_backend.repository.UserRepository;
import com.qlyshopphone_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseReponse implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public ResponseEntity<?> getAllEmployees() {
        return getResponseEntity(employeeRepository.getEmployeeList());
    }

    @Override
    public ResponseEntity<?> getEmployeeById(int id) {
        return getResponseEntity(employeeRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> saveEmployee(UsersDTO usersDTO) {
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
    @Transactional
    public void saveEmployeeRoles(int userId) throws SQLException {
        userDAO.updateEmployeeStatusAndRole(userId);
    }

    @Override
    public ResponseEntity<?> deleteEmployeeById(int id) {
        employeeDAO.deleteEmployee(id);
        return getResponseEntity("Employee deleted successfully");
    }

    @Override
    public ResponseEntity<?> searchEmployeeById(int id) {
        return getResponseEntity(employeeRepository.searchEmployeeId(id));
    }

    @Override
    public ResponseEntity<?> searchEmployeeByName(String employeeName) {
        return getResponseEntity(employeeRepository.searchEmployeeName(employeeName));
    }

    @Override
    public ResponseEntity<?> searchEmployeeByPhoneNumber(String phoneNumber) {
        return getResponseEntity(employeeRepository.searchPhoneNumber(phoneNumber));
    }

    @Override
    public ResponseEntity<?> searchEmployeeByActive(int number) {
        switch (number) {
            case 1:
                return getResponseEntity(employeeRepository.getEmployeeList());
            case 2:
                return getResponseEntity(employeeRepository.searchByAllActive());
            case 3:
                return getResponseEntity(employeeRepository.searchByNoActive());
            default:
                return getResponseEntity("Employee not found");

        }
    }
}
