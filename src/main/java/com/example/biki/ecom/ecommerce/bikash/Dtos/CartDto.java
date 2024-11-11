package com.example.biki.ecom.ecommerce.bikash.Dtos;


import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private Long id;
    private Long userId;
    private List<CartItemDto> cartItems;


}
