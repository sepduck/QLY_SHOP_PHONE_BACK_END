package com.qlyshopphone_backend.controller.rest;

import com.qlyshopphone_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class RestCustomerController {
    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/customer")
    public ResponseEntity<?> getCustomer(){
        return customerService.getAllCustomers();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") int customerId){
        return customerService.deleteCustomerById(customerId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/customer/search-id/{customerId}")
    public ResponseEntity<?> searchCustomerId(@PathVariable("customerId") int customerId){
        return customerService.searchCustomerById(customerId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/customer/search-name/{customer-name}")
    public ResponseEntity<?> searchCustomerName(@PathVariable("customer-name") String customerName){
        return customerService.searchCustomerByName(customerName);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/customer/search-phone-number/{phone-number}")
    public ResponseEntity<?> searchCustomerPhoneNumber(@PathVariable("phone-number") String phoneNumber){
        return customerService.searchCustomerByPhone(phoneNumber);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/customer/search-email/{email}")
    public ResponseEntity<?> searchCustomerEmail(@PathVariable("email") String email){
        return customerService.searchCustomerByEmail(email);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/customer/search-address/{address}")
    public ResponseEntity<?> searchCustomerAddress(@PathVariable("address") String address){
        return customerService.searchCustomerByAddress(address);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/customer/search-active/{active}")
    public ResponseEntity<?> searchCustomerActive(@PathVariable("active") byte active){
        return customerService.searchCustomerByActive(active);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    @GetMapping("/customer/search-gender/{number}")
    public ResponseEntity<?> searchCustomerNumber(@PathVariable("number") int number){
        return customerService.searchCustomerByGender(number);
    }
}
