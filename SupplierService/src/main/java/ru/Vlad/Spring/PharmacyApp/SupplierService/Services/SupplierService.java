package ru.Vlad.Spring.PharmacyApp.SupplierService.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Vlad.Spring.PharmacyApp.SupplierService.Models.Supplier;
import ru.Vlad.Spring.PharmacyApp.SupplierService.Repositories.SupplierRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public void save(Supplier supplier) {
        supplier.setStartedPartnershipAt(LocalDateTime.now());
        supplierRepository.save(supplier);
    }

    public Supplier findByName(String name) {
        Optional<Supplier> optionalSupplier = supplierRepository.findSupplierByName(name);
        if(optionalSupplier.isPresent()) {
            return optionalSupplier.get();
        }else {
            throw new RuntimeException("Supplier Not Found!");
        }
    }

    public void delete(Supplier supplier) {
        supplierRepository.delete(supplier);
    }
}
