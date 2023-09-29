package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.DTO.OrderDTO;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.Order;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.OrderDetails;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Services.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String getOrders(Model model) {
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orders",orderList);
        return "Orders";
    }

    @GetMapping("/{id}")
    public String showOrder(@PathVariable("id") Long id, Model model) {
        Optional<Order> orderOptional = orderService.getById(id);
        if(orderOptional.isPresent()) {
            model.addAttribute("order",orderOptional.get());
            return "ShowOrder";
        }
        return "OrderNotFound";
    }

    @GetMapping("/{id}/details/{detailId}")
    public String showCurrentOrderDetails(@PathVariable("id") Long id,@PathVariable("detailId") Long detailId,Model model) {
        Optional<Order> orderOptional = orderService.getById(id);
        if(orderOptional.isPresent()) {
            Optional<OrderDetails> orderDetailsOptional = orderService.getDetailsById(detailId);
            if(orderDetailsOptional.isPresent() ) {
                model.addAttribute("orderDetail", orderDetailsOptional.get());
                return "ShowOrderDetail";
            }else  {
                return "OrderDetailNotFound";
            }
        }
        return "OrderNotFound";
    }

    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        Order order = new Order();
        order.setId((long) orderService.findAll().toArray().length + 1) ;
        order.setOrderDetailsList(new ArrayList<>());
        if(orderService.save(order) == null) {
            if(orderService.getById(order.getId() - 1).isPresent()) {
                order = orderService.getById(order.getId() - 1).get();
            }
        }
        List<OrderDetails> orderDetailsList = order.getOrderDetailsList();
        model.addAttribute("orderDetailList",orderDetailsList);
        model.addAttribute("order", order);
        return "Order_Create";
    }


    @PostMapping("/create")
    public String creteOrder(@ModelAttribute Order order) {
        Order order1 = orderService.findAll().get(orderService.findAll().size() - 1);
        order1.setOrderDateTime(LocalDateTime.now());
        order1.setEmployeeId(order.getEmployeeId());
        order1.setCustomerId(order.getCustomerId());
        orderService.updateOrder(order1);
        return "redirect:/orders";
    }



    private Order convertFromDTO(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO,Order.class);
    }
}
