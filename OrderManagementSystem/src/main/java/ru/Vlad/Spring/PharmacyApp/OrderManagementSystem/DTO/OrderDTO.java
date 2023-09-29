package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.OrderDetails;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Services.OrderService;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
    private Long customerId;
    private Long employeeId;


    @Override
    public String toString() {
        return "OrderDTO{" +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                '}';
    }
}
