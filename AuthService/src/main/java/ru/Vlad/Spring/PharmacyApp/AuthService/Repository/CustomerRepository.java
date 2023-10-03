package ru.Vlad.Spring.PharmacyApp.AuthService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Integer> {
    Optional<Customer> findByUsername(String customerName);
}
