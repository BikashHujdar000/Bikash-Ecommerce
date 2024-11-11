package com.example.biki.ecom.ecommerce.bikash.Dtos;

import lombok.Data;

@Data
public class CartItemDto {

    private Long id;

    private Long productId;

    private Integer quantity;

    private  double price;

}
