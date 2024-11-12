package com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl;

import com.example.biki.ecom.ecommerce.bikash.Dtos.OrderItemDto;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.OrderItService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItServiceImpl implements OrderItService {
    @Override
    public OrderItemDto createOrderItem(Long orderId, Long productId, int quantity, double price) {
        return null;
    }

    @Override
    public List<OrderItemDto> getOrderItemsByOrderId(Long orderId) {
        return List.of();
    }

    @Override
    public OrderItemDto updateOrderItemQuantity(Long orderItemId, int quantity) {
        return null;
    }

    @Override
    public void removeOrderItem(Long orderItemId) {


    }
}
