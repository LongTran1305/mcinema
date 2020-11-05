package com.projects4.mcinema.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer implements UserDetails{
    @Id
    private String phoneNumber;
    private String password;
    private String customerName;
    private String email;
    private String address;
    private boolean isActive;

    public Customer(Customer optionalCustomer) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority();
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
//    @Builder.Default
//    private UserRole userRole = UserRole.USER;

    @Builder.Default
    private Boolean locked = false;

    @Builder.Default
    private Boolean enabled = false;
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return isActive == customer.isActive &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, password, customerName, email, address, isActive);
    }


}
