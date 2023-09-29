package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="OrderDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "medicine_id")
    private Long medicineId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order orderOwner;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", medicineId=" + medicineId +
                ", quantity=" + quantity +
                ", pricePerUnit=" + pricePerUnit +
                ", orderOwner=" + orderOwner +
                '}';
    }
}