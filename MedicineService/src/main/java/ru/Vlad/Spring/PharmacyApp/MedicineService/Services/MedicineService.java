package ru.Vlad.Spring.PharmacyApp.MedicineService.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Models.Medicine;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Repositories.MedicineRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final WebClient webClient;

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public void placeMedicine(Medicine medicine) {
        Boolean res = webClient.post()
                .uri("http://localhost:8080/api/inventory/medicines/add",uriBuilder -> uriBuilder.queryParam("medicineName",medicine.getSkuCode()).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        System.out.println(res+" BELLO");
        if(res)
            medicineRepository.save(medicine);
        else
            throw new RuntimeException("Medicine is present in Inventory,cannot add again");
    }

    @Transactional
    public void removeMedicine(String skuCode)  {
        Boolean res = webClient.post()
                .uri("http://localhost:8080/api/inventory/delete",uriBuilder -> uriBuilder.queryParam("skuCode", skuCode).build())
                .retrieve()
                .bodyToMono(Boolean.class).block();
        System.out.println(skuCode);
        System.out.println(res);
        if(medicineRepository.findMedicinesBySkuCode(skuCode).isPresent()) {
            System.out.println(medicineRepository.findMedicinesBySkuCode(skuCode).get());
        }
        if(res) {
            medicineRepository.deleteMedicineBySkuCode(skuCode);
        }else {
            throw new RuntimeException("There is not a medicine with such a name in Inventory");
        }
    }

}
