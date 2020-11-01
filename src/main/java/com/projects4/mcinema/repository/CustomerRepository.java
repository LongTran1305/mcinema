package com.projects4.mcinema.repository;

import com.projects4.mcinema.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findByPhoneNumber(String phoneNumber);
}
