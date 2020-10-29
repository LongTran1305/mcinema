package com.projects4.mcinema.service;

import com.projects4.mcinema.model.Customer;
import com.projects4.mcinema.repository.CustomerRepository;
import com.projects4.mcinema.security.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ConfirmationTokenService confirmationTokenService;

    private final EmailSenderService emailSenderService;




    void sendConfirmationMail(String userMail, String token) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Mail Confirmation Link!");
        mailMessage.setFrom("tranphanlong1997@gmail.com");
        mailMessage.setText(
                "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/sign-up/confirm?token="
                        + token);

        emailSenderService.sendEmail(mailMessage);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);

        return (UserDetails) optionalCustomer.orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));

    }
    public void signUpUser(Customer customer) {

        final String encryptedPassword = bCryptPasswordEncoder.encode(customer.getPassword());

        customer.setPassword(encryptedPassword);

        final Customer createdCustomer = customerRepository.save(customer);

        final ConfirmationToken confirmationToken = new ConfirmationToken(customer);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        sendConfirmationMail(customer.getEmail(), confirmationToken.getConfirmationToken());

    }

    void confirmUser(ConfirmationToken confirmationToken) {

        final Customer customer = confirmationToken.getCustomer();

        customer.setEnabled(true);

        customerRepository.save(customer);

        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

    }
}
