package com.example.biki.ecom.ecommerce.bikash.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long productId;
    private int quantity;
    private double price;

}

