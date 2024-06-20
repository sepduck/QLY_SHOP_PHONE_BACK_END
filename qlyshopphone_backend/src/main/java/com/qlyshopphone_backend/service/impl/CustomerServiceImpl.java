package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.dao.CustomerDAO;
import com.qlyshopphone_backend.dto.UsersDTO;
import com.qlyshopphone_backend.model.BaseReponse;
import com.qlyshopphone_backend.repository.CustomerRepository;
import com.qlyshopphone_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseReponse implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public ResponseEntity<?> getAllCustomers() {
        return getResponseEntity(customerRepository.getCustomer());
    }

    @Override
    public ResponseEntity<?> saveCustomer(UsersDTO usersDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> getCustomerById(int userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCustomerById(int userId) {
        customerDAO.deleteCustomer(userId);
        return getResponseEntity("Delete Customer Success");
    }

    @Override
    public ResponseEntity<?> searchCustomerByName(String fullName) {
        return getResponseEntity(customerRepository.searchCustomerName(fullName));
    }

    @Override
    public ResponseEntity<?> searchCustomerByEmail(String customerEmail) {
        return getResponseEntity(customerRepository.searchByEmail(customerEmail));
    }

    @Override
    public ResponseEntity<?> searchCustomerByPhone(String customerPhone) {
        return getResponseEntity(customerRepository.searchByPhoneNumber(customerPhone));
    }

    @Override
    public ResponseEntity<?> searchCustomerById(int userId) {
        return getResponseEntity(customerRepository.searchCustomerId(userId));
    }

    @Override
    public ResponseEntity<?> searchCustomerByAddress(String address) {
        return getResponseEntity(customerRepository.searchByAddress(address));
    }

    @Override
    public ResponseEntity<?> searchCustomerByActive(byte active) {
        return getResponseEntity(customerRepository.searchByActive(active));
    }

    @Override
    public ResponseEntity<?> searchCustomerByGender(int number) {
        switch (number) {
            case 1:
                return getResponseEntity(customerRepository.getCustomer());
            case 2:
                return getResponseEntity(customerRepository.searchByGenderId1());
            case 3:
                return getResponseEntity(customerRepository.searchByGenderId2());
            case 4:
                return getResponseEntity(customerRepository.searchByGenderId3());
            default:
                return getResponseEntity("Customer Not Found");
        }
    }
}
