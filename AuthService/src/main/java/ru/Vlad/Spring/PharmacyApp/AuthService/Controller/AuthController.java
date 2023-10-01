package ru.Vlad.Spring.PharmacyApp.AuthService.Controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Vlad.Spring.PharmacyApp.AuthService.DTO.AuthenticationDTO;
import ru.Vlad.Spring.PharmacyApp.AuthService.DTO.CustomerDTO;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Customer;
import ru.Vlad.Spring.PharmacyApp.AuthService.Security.CustomerService;
import ru.Vlad.Spring.PharmacyApp.AuthService.Security.JWTUtil;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("customer") CustomerDTO customerDTO) {
        return "/auth/registration";
    }

    @PostMapping(value = "/registration", produces = "text/html")
    public String performRegistrationModel(@ModelAttribute CustomerDTO customerDTO,Model model) {
        Customer customer = convertToCustomer(customerDTO);

        customerService.register(customer);

        String token = jwtUtil.generateToken(customer.getCustomerName());

        model.addAttribute("token", token);

        return "/auth/jwt";
    }

    @PostMapping(value = "/registration", produces = "application/json")
    @ResponseBody
    public Map<String, String> performRegistrationJSON(@RequestBody CustomerDTO customerDTO) {
        Customer customer = convertToCustomer(customerDTO);

        customerService.register(customer);

        String token = jwtUtil.generateToken(customer.getCustomerName());

        return Map.of("jwt-token", token);
    }

    /*@PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getCustomerName(),authenticationDTO.getCustomerPassword());
        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e) {
            return Map.of("message","Incorrect credentials");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getCustomerName());
        return Map.of("jwt-token",token);
    }*/





    @GetMapping("/auth/denied")
    public String accessDenied() {
        return "/auth/denied";
    }

    public Customer convertToCustomer(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }
}
