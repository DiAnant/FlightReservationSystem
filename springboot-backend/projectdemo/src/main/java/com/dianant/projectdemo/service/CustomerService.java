package com.dianant.projectdemo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dianant.projectdemo.model.Customer;
import com.dianant.projectdemo.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    public ResponseEntity<String> createCustomer(Customer customer) {

        // checking if a customer with this phone number exists or not
        if(customerRepository.existsByPhoneNumber(customer.getPhoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already in use. Please choose a different phone number");
        }

        // checking if a customer with this email exists or not
        if(customerRepository.existsByEmailId(customer.getEmailId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Address already in use. Please choose a different Email-Id");
        }

        // setting up customer creation date
        customer.setCustomerCreationDate(LocalDate.now());

        customerRepository.save(customer);
        return ResponseEntity.ok("Customer Record Saved Successfully!");
    }

    public ResponseEntity<Customer> getCustomerById(Long id) {
        
        Customer customer = customerRepository.findById(id).
        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No customer with id: " + id + " exists in the databse!"));
        
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<String> updateCustomer(Long id, Customer updatedCustomer) {
        
        verifyCustomerIdExistsInDatabase(id);

        Customer oldCustomer = customerRepository.getById(id);

        // checking if phone field has been altered in this update or not
        if(! oldCustomer.getPhoneNumber().equals(updatedCustomer.getPhoneNumber())){
            // making sure that this newly entered phone number isn't conflicting with anybody else's
            if(customerRepository.existsByPhoneNumber(updatedCustomer.getPhoneNumber())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This Phone Number Already Exists in the DB. Please enter a new phone number!");
            }
        }

        // checking if email field has been altered in this update or not
        if(! oldCustomer.getEmailId().equals(updatedCustomer.getEmailId())){
            // making sure that this newly entered emailId isn't conflicting with anybody else's
            if(customerRepository.existsByEmailId(updatedCustomer.getEmailId())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This Email Address Already Exists in the DB. Please enter a new email address!");
            }
        }

        updatedCustomer.setId(id); 
        updatedCustomer.setCustomerCreationDate(oldCustomer.getCustomerCreationDate());
        customerRepository.save(updatedCustomer);
        return ResponseEntity.ok("Customer Record Updated Successfully!");
    }

    public ResponseEntity<String> deleteCustomer(Long id) {

        verifyCustomerIdExistsInDatabase(id);
        
        customerRepository.deleteById(id);
        return ResponseEntity.ok("Customer Record Deleted Successfully!");
    }

    public ResponseEntity<List<Customer>> searchCustomersByName(String value) {

        List<Customer> searchResults = customerRepository.findByFirstNameContaining(value);
        List<Customer> lastNameResults = customerRepository.findByLastNameContaining(value);
        lastNameResults.forEach(customer -> searchResults.add(customer));

        return ResponseEntity.ok(searchResults);
    }

    public ResponseEntity<List<Customer>> searchCustomersByPhone(String value) {
        List<Customer> searchResults = customerRepository.findByPhoneNumberContaining(value);
        return ResponseEntity.ok(searchResults);
    }

    public ResponseEntity<List<Customer>> searchCustomersByEmail(String value) {
        List<Customer> searchResults = customerRepository.findByEmailIdContaining(value);
        return ResponseEntity.ok(searchResults);
    }

    // checking if a record with customerId: id exists in the database or not
    private void verifyCustomerIdExistsInDatabase(Long id){
        if(! customerRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"No customer with Id: " + id + " found in database. Please enter a valid customer id.");
        }
    }
}
