package com.example.biki.ecom.ecommerce.bikash.Services.AllServices;

import com.example.biki.ecom.ecommerce.bikash.Dtos.OrderItemDto;

import java.util.List;

public interface OrderItService {

    OrderItemDto createOrderItem(Long orderId, Long productId, int quantity, double price);

    List<OrderItemDto> getOrderItemsByOrderId(Long orderId);


    OrderItemDto updateOrderItemQuantity(Long orderItemId, int quantity);


    void removeOrderItem(Long orderItemId);
}
