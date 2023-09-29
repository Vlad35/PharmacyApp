package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.DTO.OrderDetailsDTO;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.Order;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.OrderDetails;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Services.OrderService;

import java.util.Optional;

@Controller
@RequestMapping("/{orderId}/orderDetail")
public class OrderDetailController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderDetailController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String createOrderDetailPage(@PathVariable("orderId") Long id, Model model) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        model.addAttribute("orderId",id);
        model.addAttribute("detail", orderDetailsDTO);
        return "Detail_Create";
    }

    @PostMapping("/create")
    public String createOrderDetail(@PathVariable("orderId") Long id, @ModelAttribute OrderDetailsDTO orderDetailsDTO) {
        OrderDetails orderDetails = convertToOrderDetails(orderDetailsDTO);
        Optional<Order> optionalOrder = orderService.getById(id);
        if (optionalOrder.isPresent()) {
            orderDetails.setOrderOwner(optionalOrder.get());
            orderService.saveOrderDetails(orderDetails);
            return "redirect:/orders/create";
        }
        return "OrderNotFound";
    }

    private OrderDetails convertToOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO,OrderDetails.class);
    }
}
