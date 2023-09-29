package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "order_date")
    private LocalDateTime orderDateTime;

    @OneToMany(mappedBy = "orderOwner")
    private List<OrderDetails> orderDetailsList;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", orderDateTime=" + orderDateTime +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }
}
