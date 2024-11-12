package com.example.biki.ecom.ecommerce.bikash.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

        private Long id;
        private Long userId;
        private List<OrderItemDto> orderItems;
        private String status;
        private double totalAmount;
        private LocalDateTime orderDate;


    }
