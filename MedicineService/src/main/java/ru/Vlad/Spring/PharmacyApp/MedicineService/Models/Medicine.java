package ru.Vlad.Spring.PharmacyApp.MedicineService.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="Medicine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "skuCode",unique = true)
    private String skuCode;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + skuCode + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", expiryDate=" + expiryDate +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
