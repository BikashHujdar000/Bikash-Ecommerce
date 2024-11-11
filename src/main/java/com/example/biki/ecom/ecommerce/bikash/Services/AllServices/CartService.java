package com.example.biki.ecom.ecommerce.bikash.Services.AllServices;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CartDto;

public interface CartService {

    CartDto createCartForUser(Long userId);

    // get cart By Id

    CartDto getCartForUser(Long userId);
}
