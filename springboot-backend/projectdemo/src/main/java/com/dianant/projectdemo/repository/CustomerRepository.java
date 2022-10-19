package com.dianant.projectdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dianant.projectdemo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    public List<Customer> findByFirstNameContaining(String keyword);
    public List<Customer> findByLastNameContaining(String keyword);
    public List<Customer> findByPhoneNumberContaining(String keyword);
    public List<Customer> findByEmailIdContaining(String keyword);
    public boolean existsByEmailId(String emailId);
    public boolean existsByPhoneNumber(String phoneNumber);
    public Optional<Customer> findByEmailId(String email);
}
