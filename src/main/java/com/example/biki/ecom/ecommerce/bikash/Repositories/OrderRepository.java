package com.example.biki.ecom.ecommerce.bikash.Repositories;

import com.example.biki.ecom.ecommerce.bikash.Entities.Order;
import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order,Long> {
    // Fetch orders by user
    List<Order> findByUser(User user);

    // Fetch orders by status
    List<Order> findByStatus(String status);


}
