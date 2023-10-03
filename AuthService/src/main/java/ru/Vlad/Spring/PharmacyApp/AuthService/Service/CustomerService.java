package ru.Vlad.Spring.PharmacyApp.AuthService.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Customer;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Role;
import ru.Vlad.Spring.PharmacyApp.AuthService.Repository.CustomerRepository;
import ru.Vlad.Spring.PharmacyApp.AuthService.Repository.RoleRepository;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    public void register(Customer customer) {
        if(customer.getUsername() == null  || customer.getPassword() == null|| customer.getYearOfBirth() == 0 || customer.getEmail() == null) {
            throw  new RuntimeException("User is not registered");
        }
        Role role = roleRepository.findAll().get(0);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setRoles(Collections.singleton(role));
        customerRepository.save(customer);
    }
}
