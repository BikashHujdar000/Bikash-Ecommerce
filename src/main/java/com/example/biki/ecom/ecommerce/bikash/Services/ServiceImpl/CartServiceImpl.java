package com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CartDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.Cart;
import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ResourceNotFound;
import com.example.biki.ecom.ecommerce.bikash.Repositories.CartRepository;
import com.example.biki.ecom.ecommerce.bikash.Repositories.UserRepository;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl  implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDto createCartForUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "userId", userId));
        Cart cart = new Cart();
        cart.setUser(user);

        Cart savedCart = cartRepository.save(cart);

        return modelMapper.map(savedCart, CartDto.class);
    }

    @Override
    public CartDto getCartForUser(Long userId) {

        Cart cart = this.cartRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFound("Cart", "userId", userId));
        return modelMapper.map(cart, CartDto.class);

    }

}

