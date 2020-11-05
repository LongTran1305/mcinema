package com.projects4.mcinema;

import com.projects4.mcinema.model.Customer;
import com.projects4.mcinema.model.Role;
import com.projects4.mcinema.repository.CustomerRepository;
import com.projects4.mcinema.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class McinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(McinemaApplication.class, args);
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public CommandLineRunner init(CustomerRepository customerRepository, RoleRepository roleRepository) {
        return args -> {
            Role roleCustomer = roleRepository.findByName("ROLE_CUSTOMER");
            if (roleCustomer == null) {
                roleCustomer = new Role();
                roleCustomer.setName("ROLE_CUSTOMER");

                roleRepository.save(roleCustomer);
            }

            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            if (roleAdmin == null) {
                roleAdmin = new Role();
                roleAdmin.setName("ROLE_ADMIN");

                roleRepository.save(roleAdmin);
            }

            Customer customer = customerRepository.findByEmail("customer@gmail.com");
            if (customer == null) {
                customer = new Customer();
                customer.setEmail("customer@gmail.com");
                customer.setPassword(bCryptPasswordEncoder.encode("123456"));
                customer.setPhoneNumber("0123456789");
                customer.setAddress("HCMC");
                customer.setFullName("Customer");

                Set<Role> customerRole = new HashSet<>();
                customerRole.add(roleCustomer);
                customer.setRoles(customerRole);

                customerRepository.save(customer);
            }

            Customer admin = customerRepository.findByEmail("admin@gmail.com");
            if (admin == null) {
                admin = new Customer();
                admin.setEmail("admin@gmail.com");
                admin.setPassword(bCryptPasswordEncoder.encode("123456"));
                admin.setPhoneNumber("0123456788");
                admin.setAddress("HCMC");
                admin.setFullName("Admin");

                Set<Role> adminRole = new HashSet<>();
                adminRole.add(roleAdmin);
                admin.setRoles(adminRole);

                customerRepository.save(admin);
            }
        };
    }

}
