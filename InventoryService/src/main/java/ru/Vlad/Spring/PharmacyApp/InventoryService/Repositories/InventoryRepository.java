package ru.Vlad.Spring.PharmacyApp.InventoryService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Vlad.Spring.PharmacyApp.InventoryService.Models.Inventory;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findBySkuCodeIn(List<String> stringList);
    Optional<Inventory> findBySkuCode(String skuCode);
}
