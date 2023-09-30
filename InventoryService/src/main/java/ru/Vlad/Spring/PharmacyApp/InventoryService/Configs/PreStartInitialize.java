package ru.Vlad.Spring.PharmacyApp.InventoryService.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.Vlad.Spring.PharmacyApp.InventoryService.Models.Inventory;
import ru.Vlad.Spring.PharmacyApp.InventoryService.Repositories.InventoryRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class PreStartInitialize implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public PreStartInitialize(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Создаем 7 объектов Inventory
        if(inventoryRepository.findAll().isEmpty()) {
            Inventory item1 = new Inventory();
            item1.setSkuCode("Aspirin100mg");
            item1.setQuantity(100L);

            Inventory item2 = new Inventory();
            item2.setSkuCode("Ibuprofen200mg");
            item2.setQuantity(50L);

            Inventory item3 = new Inventory();
            item3.setSkuCode("Paracetamol500mg");
            item3.setQuantity(200L);

            Inventory item4 = new Inventory();
            item4.setSkuCode("Loratadine10mg");
            item4.setQuantity(75L);

            Inventory item5 = new Inventory();
            item5.setSkuCode("CoughSyrup");
            item5.setQuantity(30L);

            Inventory item6 = new Inventory();
            item6.setSkuCode("Bandages");
            item6.setQuantity(150L);

            Inventory item7 = new Inventory();
            item7.setSkuCode("HydrogenPeroxide");
            item7.setQuantity(50L);


            // Сохраняем объекты в базу данных
            List<Inventory> inventoryList = Arrays.asList(item1, item2, item3,item4,item5,item6,item7);
            inventoryRepository.saveAll(inventoryList);
        }
    }
}
