package com.projects4.mcinema.service;

import com.projects4.mcinema.model.Customer;
import com.projects4.mcinema.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        Customer optionalCustomer = customerRepository.findByPhoneNumber(phoneNumber);
        if (optionalCustomer == null) {
            throw new UsernameNotFoundException(phoneNumber);
        }
        return new Customer(optionalCustomer);
        //return (UserDetails) optionalCustomer.orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));

    }



}
