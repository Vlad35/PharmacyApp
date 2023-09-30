package ru.Vlad.Spring.PharmacyApp.InventoryService.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="skuCode",unique = true)
    private String skuCode;
    @Column(name = "Quantity")
    private Long quantity;
}
