package com.projects4.mcinema.repository;

import com.projects4.mcinema.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findByPhoneNumber(String phoneNumber);
}
