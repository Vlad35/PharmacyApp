package ru.Vlad.Spring.PharmacyApp.SupplierService.Controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Vlad.Spring.PharmacyApp.SupplierService.DTO.SupplierDTO;
import ru.Vlad.Spring.PharmacyApp.SupplierService.Models.Supplier;
import ru.Vlad.Spring.PharmacyApp.SupplierService.Services.SupplierService;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Supplier> allSuppliers() {
        return supplierService.getAll();
    }

    @GetMapping("/all")
    public String allSuppliers(Model model) {
        List<Supplier> supplierList = supplierService.getAll();
        model.addAttribute("suppliers",supplierList);
        return "Supplier_Index";
    }

    @GetMapping("/create")
    public String newSupplierPage(Model model) {
        SupplierDTO supplierDTO = new SupplierDTO();
        model.addAttribute("supplier",supplierDTO);
        return "Supplier_New";
    }

    @PostMapping("/create")
    public String newSupplier(@ModelAttribute SupplierDTO supplierDTO) {
        Supplier supplier = convertFromDTO(supplierDTO);
        supplierService.save(supplier);

        return "redirect:/api/suppliers/all";
    }

    @GetMapping("/delete")
    public String removeSupplierPage(Model model) {
        String name = "";
        model.addAttribute("name", name);

        return "Supplier_Delete";
    }

    @PostMapping("/delete")
    public String removeSupplier(@RequestParam("name") String name) {
        Supplier supplier = supplierService.findByName(name);
        supplierService.delete(supplier);

        return "redirect:/api/suppliers/all";
    }

    private Supplier convertFromDTO(SupplierDTO supplierDTO) {
        return modelMapper.map(supplierDTO,Supplier.class);
    }
}
