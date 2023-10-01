package ru.Vlad.Spring.PharmacyApp.AuthService.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Customer;
import ru.Vlad.Spring.PharmacyApp.AuthService.Repository.CustomerRepository;
import ru.Vlad.Spring.PharmacyApp.AuthService.Security.CustomerDetails;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalUser = customerRepository.findByCustomerName(username);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        return new CustomerDetails(optionalUser.get());
    }
}
