package ru.Vlad.Spring.PharmacyApp.MedicineService.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Models.Medicine;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Models.Supplier;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Repositories.MedicineRepository;

import java.util.List;
import java.util.Objects;

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

    public List<Supplier> getSuppliers() {
        ParameterizedTypeReference<List<Supplier>> typeReference = new ParameterizedTypeReference<List<Supplier>>() {};

        ResponseEntity<List<Supplier>> responseEntity = webClient
                .get()
                .uri("http://localhost:8083/api/suppliers")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(typeReference)
                .block();
        assert responseEntity != null;
        if(Objects.requireNonNull(responseEntity.getBody()).isEmpty()) {
            throw new RuntimeException("There is no Manufacturer Registered!");
        }
        return responseEntity.getBody();
    }

}
