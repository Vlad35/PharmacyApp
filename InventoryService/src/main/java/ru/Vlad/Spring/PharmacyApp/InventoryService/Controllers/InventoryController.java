package ru.Vlad.Spring.PharmacyApp.InventoryService.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Vlad.Spring.PharmacyApp.InventoryService.Models.Inventory;
import ru.Vlad.Spring.PharmacyApp.InventoryService.Services.InventoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/medicines/delete")
    @ResponseStatus(HttpStatus.OK)
    public Boolean findInventoryBySkuCode(@RequestParam("skuCode") String skuCode) {
        System.out.println(skuCode+"ghvjhbkjghgxfchvjbkhjghf");
        Optional<Inventory> inventoryOptional = inventoryService.findBySkuCode(skuCode);
        if(inventoryOptional.isPresent()) {
            System.out.println(inventoryOptional.get().getSkuCode());
            inventoryService.delete(inventoryOptional.get());
            return true;
        }
        return false;
    }

    @PostMapping("/medicines/add")
    public Boolean addMedicine(@RequestParam("medicineName") String medicineName) {
        return inventoryService.saveMedicine(medicineName);
    }


    @GetMapping
    public List<Inventory> index(Model model) {
        return inventoryService.findAll();
    }
}
