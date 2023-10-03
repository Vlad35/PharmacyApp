package ru.Vlad.Spring.PharmacyApp.AuthService.Configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Customer;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Role;
import ru.Vlad.Spring.PharmacyApp.AuthService.Repository.CustomerRepository;
import ru.Vlad.Spring.PharmacyApp.AuthService.Repository.RoleRepository;

import java.time.LocalDateTime;
import java.util.Collections;


@RequiredArgsConstructor
@Component
public class PreStartInitialization implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setName("ROLE_USER");
        role.setDescription("USERS ROLE GRANTS");
        roleRepository.save(role);

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDateTime.now());
        customer.setPassword("$2a$10$UpP9usiM3oWNDaA8/BJeOeJpuroJsO709GFKYFsWV3Imtq59KyfHm");
        customer.setUsername("Name");
        customer.setEmail("user@mail.com");
        customer.setRoles(Collections.singleton(role));
        customer.setYearOfBirth(2004);
        customerRepository.save(customer);

    }
}
