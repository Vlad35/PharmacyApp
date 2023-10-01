package ru.Vlad.Spring.PharmacyApp.SupplierService.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.Vlad.Spring.PharmacyApp.SupplierService.Models.Supplier;
import ru.Vlad.Spring.PharmacyApp.SupplierService.Repositories.SupplierRepository;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;

@Component
public class PreStartInitialize implements CommandLineRunner {

    private final SupplierRepository supplierRepository;
    private final Faker faker;

    @Autowired
    public PreStartInitialize(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.faker = new Faker(new Locale("ru")); // Устанавливаем локаль для русских данных
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            Supplier supplier = new Supplier();
            supplier.setName(faker.name().fullName());
            supplier.setEmail(faker.internet().emailAddress());
            supplier.setStartedPartnershipAt(LocalDateTime.now());

            supplierRepository.save(supplier);
        }
    }
}
