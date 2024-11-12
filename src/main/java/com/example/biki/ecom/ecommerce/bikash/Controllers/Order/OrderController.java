package com.example.biki.ecom.ecommerce.bikash.Controllers.Order;

import com.example.biki.ecom.ecommerce.bikash.Dtos.OrderDto;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ApiResponse;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId) {


            OrderDto orderDto = orderService.createOrder(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable Long userId) {
        List<OrderDto> orderDtos = orderService.getOrdersByUser(userId);
       return  ResponseEntity.status(HttpStatus.OK).body(orderDtos);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        OrderDto orderDto = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderDto);
    }


    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {
        OrderDto updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @PatchMapping("/cancel/{orderId}")
    public  ResponseEntity<ApiResponse> removeOrder(@PathVariable ("orderId") Long orderId)
    {
        this.orderService.cancelOrder(orderId);
        ApiResponse response = new ApiResponse("Order Cancelled Successfully",true);
        return   new  ResponseEntity<>(response,HttpStatus.OK);
    }

}