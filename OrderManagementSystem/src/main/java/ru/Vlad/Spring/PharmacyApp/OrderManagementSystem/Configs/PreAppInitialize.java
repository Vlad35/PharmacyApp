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
        // Создаем и сохраняем первый заказ
        Order order1 = new Order();
        order1.setCustomerId(1L);
        order1.setEmployeeId(2L);
        order1.setOrderDateTime(LocalDateTime.now());
        orderService.save(order1);

        // Создаем и сохраняем второй заказ
        Order order2 = new Order();
        order2.setCustomerId(3L);
        order2.setEmployeeId(4L);
        order2.setOrderDateTime(LocalDateTime.now().minusDays(1)); // Пример даты
        orderService.save(order2);

        // Создаем и сохраняем третий заказ
        Order order3 = new Order();
        order3.setCustomerId(5L);
        order3.setEmployeeId(6L);
        order3.setOrderDateTime(LocalDateTime.now().minusDays(2)); // Пример даты
        orderService.save(order3);

        // Создаем и сохраняем первую деталь заказа для первого заказа
        OrderDetails orderDetails1 = new OrderDetails();
        orderDetails1.setMedicineId(101L);
        orderDetails1.setQuantity(5);
        orderDetails1.setPricePerUnit(new BigDecimal("10.50"));
        orderDetails1.setOrderOwner(order1); // Связываем с заказом
        orderService.saveOrderDetails(orderDetails1);

        // Создаем и сохраняем вторую деталь заказа для первого заказа
        OrderDetails orderDetails2 = new OrderDetails();
        orderDetails2.setMedicineId(102L);
        orderDetails2.setQuantity(3);
        orderDetails2.setPricePerUnit(new BigDecimal("8.75"));
        orderDetails2.setOrderOwner(order1); // Связываем с заказом
        orderService.saveOrderDetails(orderDetails2);

        // Создаем и сохраняем первую деталь заказа для второго заказа
        OrderDetails orderDetails3 = new OrderDetails();
        orderDetails3.setMedicineId(103L);
        orderDetails3.setQuantity(2);
        orderDetails3.setPricePerUnit(new BigDecimal("15.25"));
        orderDetails3.setOrderOwner(order2); // Связываем с заказом
        orderService.saveOrderDetails(orderDetails3);

        // Создаем и сохраняем вторую деталь заказа для второго заказа
        OrderDetails orderDetails4 = new OrderDetails();
        orderDetails4.setMedicineId(104L);
        orderDetails4.setQuantity(4);
        orderDetails4.setPricePerUnit(new BigDecimal("9.99"));
        orderDetails4.setOrderOwner(order2); // Связываем с заказом
        orderService.saveOrderDetails(orderDetails4);

        // Создаем и сохраняем первую деталь заказа для третьего заказа
        OrderDetails orderDetails5 = new OrderDetails();
        orderDetails5.setMedicineId(105L);
        orderDetails5.setQuantity(7);
        orderDetails5.setPricePerUnit(new BigDecimal("12.75"));
        orderDetails5.setOrderOwner(order3); // Связываем с заказом
        orderService.saveOrderDetails(orderDetails5);

        // Создаем и сохраняем вторую деталь заказа для третьего заказа
        OrderDetails orderDetails6 = new OrderDetails();
        orderDetails6.setMedicineId(106L);
        orderDetails6.setQuantity(1);
        orderDetails6.setPricePerUnit(new BigDecimal("18.50"));
        orderDetails6.setOrderOwner(order3); // Связываем с заказом
        orderService.saveOrderDetails(orderDetails6);
    }
}
