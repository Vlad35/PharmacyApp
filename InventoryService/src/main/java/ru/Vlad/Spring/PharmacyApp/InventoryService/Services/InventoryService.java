package ru.Vlad.Spring.PharmacyApp.InventoryService.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.Vlad.Spring.PharmacyApp.InventoryService.DTO.InventoryResponse;
import ru.Vlad.Spring.PharmacyApp.InventoryService.Models.Inventory;
import ru.Vlad.Spring.PharmacyApp.InventoryService.Repositories.InventoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory ->
                        InventoryResponse
                                .builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0 && (inventoryRepository.findBySkuCode(inventory.getSkuCode()).isPresent()) && (inventoryRepository.findBySkuCode(inventory.getSkuCode()).get().getQuantity() - inventory.getQuantity()) >= 0)
                                .build()
                ).toList();
    }


    public Optional<Inventory> findBySkuCode(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode);
    }
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Boolean saveMedicine(String medicineName) {
        Optional<Inventory> optionalInventory = inventoryRepository.findBySkuCode(medicineName);
        if(optionalInventory.isPresent()) {
            return false;
        }
        Inventory inventory = new Inventory();
        inventory.setSkuCode(medicineName);
        inventory.setQuantity(100L);
        inventoryRepository.save(inventory);
        return inventory.getQuantity() == 100;
    }
    public void delete(Inventory inventory) {
        inventoryRepository.delete(inventory);
    }
}
