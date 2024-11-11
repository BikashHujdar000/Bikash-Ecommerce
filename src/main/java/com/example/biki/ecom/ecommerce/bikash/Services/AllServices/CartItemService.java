package com.example.biki.ecom.ecommerce.bikash.Services.AllServices;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CartItemDto;

import java.util.List;

public interface CartItemService{


    CartItemDto addProductToCart(Long userId, Long productId, Integer quantity);


    CartItemDto updateCartItem(Long userId, Long cartItemId, Integer quantity);


    void removeCartItem(Long userId, Long cartItemId);


    List<CartItemDto> getCartItems(Long userId);

}
