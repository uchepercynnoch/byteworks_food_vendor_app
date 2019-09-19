package com.byteworks.devops.controllers;

import com.byteworks.devops.entities.Customer;
import com.byteworks.devops.entities.Permission;
import com.byteworks.devops.exceptions.CustomerAlreadyExistException;
import com.byteworks.devops.exceptions.DataPersistFailureException;
import com.byteworks.devops.exceptions.PasswordsNotMatchException;
import com.byteworks.devops.helpers.RegistrationStatus;
import com.byteworks.devops.repository.CustomRepository;
import com.byteworks.devops.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {
    private CustomerRepository customerRepository;
    private CustomRepository customRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setCustomRepository(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "register", consumes = "application/json")
    public ResponseEntity actionCreateCustomer(@RequestBody Customer customerCredentials, Customer customer) {
        Permission setCustomerRole = new Permission("USER", customerCredentials.getCustomerUsername());
        boolean isCustomerExist = customRepository.CUSTOMER_EXIST(customerCredentials.getCustomerUsername());
        String registrationStatus;
        if (customerCredentials.getCustomerPassword() != null && customerCredentials.getCustomerConfirmPassword() != null && !customerCredentials.getCustomerPassword().equals(customerCredentials.getCustomerConfirmPassword())) {
            throw new PasswordsNotMatchException("Passwords do not match");
        } else if (isCustomerExist) {
            throw new CustomerAlreadyExistException("Customer with username already exist");
        }
        try {
            customer.getPermissions().add(setCustomerRole);
            customer.setCustomerUsername(customerCredentials.getCustomerUsername());
            customer.setCustomerPassword(passwordEncoder.encode(customerCredentials.getCustomerPassword()));
            customer.setCustomerConfirmPassword(passwordEncoder.encode(customerCredentials.getCustomerConfirmPassword()));
            customer.setCustomerName(customerCredentials.getCustomerName());
            customer.setCustomerPhoneNumber(customerCredentials.getCustomerPhoneNumber());
            customer.setCustomerLocation(customerCredentials.getCustomerLocation());
            customerRepository.save(customer);
            ObjectMapper mapper = new ObjectMapper();
            RegistrationStatus success = new RegistrationStatus(true);
            registrationStatus = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(success);
        } catch (Exception e) {
            throw new DataPersistFailureException(e.getMessage());
        }
        return new ResponseEntity<>(registrationStatus, HttpStatus.CREATED);
    }

    @PostMapping(path = "/update_profile/{username}", consumes = "application/json", produces = "application/json")
    public ResponseEntity actionUpdateCustomer(@PathVariable("username") String customer) {
        boolean isCustomerExist = customRepository.CUSTOMER_EXIST(customer);
        if (isCustomerExist) {
            customRepository.UPDATE_CUSTOMER_PERMISSION(customer);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
