package ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Vlad.Spring.PharmacyApp.OrderManagementSystem.Models.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {
}
