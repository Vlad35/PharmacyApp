package ru.Vlad.Spring.PharmacyApp.AuthService.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String customerName;
    private String customerPassword;
    private int yearOfBirth;

    private String email;
}
