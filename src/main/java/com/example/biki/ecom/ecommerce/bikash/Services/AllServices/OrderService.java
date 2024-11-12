package com.example.biki.ecom.ecommerce.bikash.Services.AllServices;

import com.example.biki.ecom.ecommerce.bikash.Dtos.OrderDto;

import java.util.List;

public interface OrderService {


    OrderDto createOrder(Long userId);


    List<OrderDto> getOrdersByUser(Long userId);

    OrderDto getOrderById(Long orderId);


    OrderDto updateOrderStatus(Long orderId, String status);


    void cancelOrder(Long orderId);





}
