package com.example.biki.ecom.ecommerce.bikash.Controllers.Order;

import com.example.biki.ecom.ecommerce.bikash.Dtos.OrderDto;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ApiResponse;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId) {


            OrderDto orderDto = orderService.createOrder(userId);  // Call service to create order
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);

    }
}