package com.example.biki.ecom.ecommerce.bikash.Controllers;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CartItemDto;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ApiResponse;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/cart")
public class CartController {

    @Autowired
    private  CartItemService cartItemService;

    @PostMapping("/add/{userId}/{productId}/{quantity}")
    public ResponseEntity<CartItemDto> addProductToCart(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable Integer quantity) {

        // Default quantity of 1 or handle frontend logic for it
       // You can handle this if quantity is dynamic in your frontend

        CartItemDto cartItemDto =this.cartItemService.addProductToCart(userId,productId,quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);


    }



    // Update product quantity in the cart
    @PutMapping("/update/{userId}/{productId}/{cartItemId}/{quantity}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long userId, @PathVariable Long cartItemId,
                                                      @PathVariable Integer quantity) {
        CartItemDto cartItemDto = cartItemService.updateCartItem(userId, cartItemId, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    // Remove product from cart
    @DeleteMapping("/remove/{userId}/{cartItemId}")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable("userId") Long userId,
                                                      @PathVariable Long cartItemId) {
        cartItemService.removeCartItem(userId, cartItemId);
        ApiResponse response = new ApiResponse("Product removed from cart successfully", true);
        return ResponseEntity.ok(response);
    }


    // Get all cart items for a user
    @GetMapping("/get/{userId}")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable("userId") Long userId) {
        List<CartItemDto> cartItems = cartItemService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);

    }
}
