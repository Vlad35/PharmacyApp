package ru.Vlad.Spring.PharmacyApp.MedicineService.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Models.Medicine;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Repositories.MedicineRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class PreStartInitialize implements CommandLineRunner {
    private final MedicineRepository medicineRepository;

    @Autowired
    public PreStartInitialize(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Добавляем 5 разных лекарств в базу данных

        if(medicineRepository.findAll().isEmpty()) {
            // Лекарство 1
            Medicine medicine1 = new Medicine();
            medicine1.setSkuCode("Aspirin100mg");
            medicine1.setDescription("Укрепляющее лекарство для повышения жизненных сил.");
            medicine1.setPrice(new BigDecimal("10.99"));
            medicine1.setExpiryDate(LocalDate.of(2023, 12, 31));
            medicine1.setManufacturer("Фармакомпания 'Здоровье-Плюс'");
            medicineRepository.save(medicine1);

            // Лекарство 2
            Medicine medicine2 = new Medicine();
            medicine2.setSkuCode("Ibuprofen200mg");
            medicine2.setDescription("Противовирусное средство для борьбы с гриппом.");
            medicine2.setPrice(new BigDecimal("5.99"));
            medicine2.setExpiryDate(LocalDate.of(2023, 10, 15));
            medicine2.setManufacturer("Медицинская лаборатория 'ГриппСтоп'");
            medicineRepository.save(medicine2);

            // Лекарство 3
            Medicine medicine3 = new Medicine();
            medicine3.setSkuCode("Paracetamol500mg");
            medicine3.setDescription("Противоболевое средство для снятия боли.");
            medicine3.setPrice(new BigDecimal("7.49"));
            medicine3.setExpiryDate(LocalDate.of(2023, 11, 30));
            medicine3.setManufacturer("Фармацевтическая компания 'Здоровье'");
            medicineRepository.save(medicine3);

            // Лекарство 4
            Medicine medicine4 = new Medicine();
            medicine4.setSkuCode("Loratadine10mg");
            medicine4.setDescription("Витаминный комплекс для поддержания иммунитета.");
            medicine4.setPrice(new BigDecimal("8.99"));
            medicine4.setExpiryDate(LocalDate.of(2023, 9, 30));
            medicine4.setManufacturer("Биологическая лаборатория 'Вита-Мед'");
            medicineRepository.save(medicine4);

            // Лекарство 5
            Medicine medicine5 = new Medicine();
            medicine5.setSkuCode("CoughSyrup");
            medicine5.setDescription("Средство для регуляции пищеварения.");
            medicine5.setPrice(new BigDecimal("4.50"));
            medicine5.setExpiryDate(LocalDate.of(2024, 3, 15));
            medicine5.setManufacturer("Фармацевтическая фабрика 'Здоровье и Гармония'");
            medicineRepository.save(medicine5);
        }
    }
}
