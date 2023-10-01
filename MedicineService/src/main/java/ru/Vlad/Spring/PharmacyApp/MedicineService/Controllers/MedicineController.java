package ru.Vlad.Spring.PharmacyApp.MedicineService.Controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Models.Medicine;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Models.Supplier;
import ru.Vlad.Spring.PharmacyApp.MedicineService.Services.MedicineService;

import java.util.List;

@Controller
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @ResponseBody
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Medicine> showAll(Model model) {
        return medicineService.findAll();
    }

    @GetMapping("/add")
    public String addMedicinePage(Model model) {
        Medicine medicine = new Medicine();
        List<Supplier> suppliers = medicineService.getSuppliers();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("medicine",medicine);
        return "Medicine_Add";
    }

    @PostMapping("/add")
    public String addMedicine(@ModelAttribute Medicine medicine) {
        medicineService.placeMedicine(medicine);
        return "redirect:/api/medicines";
    }

    @GetMapping("/remove")
    public String removeMedicinePage(Model model) {
        String skuCode = "";
        model.addAttribute("skuCode", skuCode);
        return "Medicine_Delete";
    }

    @PostMapping("/remove")
    public String removeMedicine(@RequestParam("skuCode") String skuCode) {
        medicineService.removeMedicine(skuCode);
        return "redirect:/api/medicines";
    }
}
