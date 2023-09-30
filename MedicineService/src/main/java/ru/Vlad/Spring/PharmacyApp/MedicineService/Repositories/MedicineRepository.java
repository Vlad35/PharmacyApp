package ru.Vlad.Spring.PharmacyApp.MedicineService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Models.Medicine;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {
    Optional<Medicine> findMedicinesBySkuCode(String skuCode);
    void deleteMedicineBySkuCode(String skuCode);
}
