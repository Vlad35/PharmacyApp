package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {
    private Long medicineId;

    private int quantity;

    private BigDecimal pricePerUnit;
}
