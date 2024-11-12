package com.example.biki.ecom.ecommerce.bikash.Controllers.Cart;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CartDto;
import com.example.biki.ecom.ecommerce.bikash.Dtos.CartItemDto;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ApiResponse;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.CartItemService;
import com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Autowired
    private  CartItemService cartItemService;

    @PostMapping("/add/{userId}/{productId}/{quantity}")
    public ResponseEntity<CartItemDto> addProductToCart(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable Integer quantity) {


        CartItemDto cartItemDto =this.cartItemService.addProductToCart(userId,productId,quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);

    }


    @PutMapping("/update/{userId}/{productId}/{cartItemId}/{quantity}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long userId, @PathVariable Long cartItemId,
                                                      @PathVariable Integer quantity) {
        CartItemDto cartItemDto = cartItemService.updateCartItem(userId, cartItemId, quantity);
        return ResponseEntity.ok(cartItemDto);
    }

    // Removing product from cart
    @DeleteMapping("/remove/{userId}/{cartItemId}")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable("userId") Long userId,
                                                      @PathVariable Long cartItemId) {
        cartItemService.removeCartItem(userId, cartItemId);
        ApiResponse response = new ApiResponse("Product removed from cart successfully", true);
        return ResponseEntity.ok(response);
    }


    // Getting all cart items for a user
    @GetMapping("/get/{userId}")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable("userId") Long userId) {
        List<CartItemDto> cartItems = cartItemService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);

    }

    // get user cart

    @GetMapping("/getCart/{userId}")
    public ResponseEntity<CartDto>getUserCart(@PathVariable("userId")Long userId ){


          CartDto cartDto =  this.cartServiceImpl.getCartForUser(userId);

        return  new ResponseEntity<>(cartDto,HttpStatus.OK);
    }



}
