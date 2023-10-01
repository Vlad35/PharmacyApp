package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.Order;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.OrderDetails;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Services.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Component
public class PreAppInitialize implements CommandLineRunner {
    private final OrderService orderService;

    @Autowired
    public PreAppInitialize(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        Order order1 = new Order();
        order1.setCustomerId(1L);
        order1.setEmployeeId(2L);
        order1.setOrderDateTime(LocalDateTime.now());
        orderService.save(order1);

        Order order2 = new Order();
        order2.setCustomerId(3L);
        order2.setEmployeeId(4L);
        order2.setOrderDateTime(LocalDateTime.now().minusDays(1));
        orderService.save(order2);

        Order order3 = new Order();
        order3.setCustomerId(5L);
        order3.setEmployeeId(6L);
        order3.setOrderDateTime(LocalDateTime.now().minusDays(2));
        orderService.save(order3);

        OrderDetails orderDetails1 = new OrderDetails();
        orderDetails1.setMedicineId(101L);
        orderDetails1.setQuantity(5);
        orderDetails1.setPricePerUnit(new BigDecimal("10.50"));
        orderDetails1.setOrderOwner(order1);
        orderService.saveOrderDetails(orderDetails1);

        OrderDetails orderDetails2 = new OrderDetails();
        orderDetails2.setMedicineId(102L);
        orderDetails2.setQuantity(3);
        orderDetails2.setPricePerUnit(new BigDecimal("8.75"));
        orderDetails2.setOrderOwner(order1);
        orderService.saveOrderDetails(orderDetails2);

        OrderDetails orderDetails3 = new OrderDetails();
        orderDetails3.setMedicineId(103L);
        orderDetails3.setQuantity(2);
        orderDetails3.setPricePerUnit(new BigDecimal("15.25"));
        orderDetails3.setOrderOwner(order2);
        orderService.saveOrderDetails(orderDetails3);

        OrderDetails orderDetails4 = new OrderDetails();
        orderDetails4.setMedicineId(104L);
        orderDetails4.setQuantity(4);
        orderDetails4.setPricePerUnit(new BigDecimal("9.99"));
        orderDetails4.setOrderOwner(order2);
        orderService.saveOrderDetails(orderDetails4);

        OrderDetails orderDetails5 = new OrderDetails();
        orderDetails5.setMedicineId(105L);
        orderDetails5.setQuantity(7);
        orderDetails5.setPricePerUnit(new BigDecimal("12.75"));
        orderDetails5.setOrderOwner(order3);
        orderService.saveOrderDetails(orderDetails5);

        OrderDetails orderDetails6 = new OrderDetails();
        orderDetails6.setMedicineId(106L);
        orderDetails6.setQuantity(1);
        orderDetails6.setPricePerUnit(new BigDecimal("18.50"));
        orderDetails6.setOrderOwner(order3);
        orderService.saveOrderDetails(orderDetails6);
    }
}
