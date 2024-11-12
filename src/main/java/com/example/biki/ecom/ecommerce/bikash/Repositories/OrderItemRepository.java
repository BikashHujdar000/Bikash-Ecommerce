package com.example.biki.ecom.ecommerce.bikash.Repositories;

import com.example.biki.ecom.ecommerce.bikash.Dtos.OrderItemDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
