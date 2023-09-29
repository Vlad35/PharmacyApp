package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.Order;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.OrderDetails;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Repositories.OrderDetailsRepository;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        if(order.getCustomerId() != null && order.getEmployeeId()!= null) {
            order.setOrderDateTime(LocalDateTime.now());
            return orderRepository.save(order);
        }else if(order.getCustomerId() == null && orderRepository.findById(order.getId() - 1).get().getEmployeeId() != null) {
            return orderRepository.save(order);
        }
        return null;
    }

    public void saveOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }
    public Optional<OrderDetails> getDetailsById(Long detailId) {
        return orderDetailsRepository.findById(detailId);
    }
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
}
