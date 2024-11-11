package com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CartDto;
import com.example.biki.ecom.ecommerce.bikash.Dtos.CartItemDto;
import com.example.biki.ecom.ecommerce.bikash.Dtos.UserDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.Cart;
import com.example.biki.ecom.ecommerce.bikash.Entities.CartItem;
import com.example.biki.ecom.ecommerce.bikash.Entities.Product;
import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ResourceNotFound;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.UnauthorizedException;
import com.example.biki.ecom.ecommerce.bikash.Repositories.CartItemRepository;
import com.example.biki.ecom.ecommerce.bikash.Repositories.CartRepository;
import com.example.biki.ecom.ecommerce.bikash.Repositories.ProductRepository;
import com.example.biki.ecom.ecommerce.bikash.Repositories.UserRepository;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.CartItemService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;


    @Override
    public CartItemDto addProductToCart(Long userId, Long productId, Integer quantity) {
        // Find or create a cart for the user

        // boolean existsByUser(User user);

        Cart cart = null;

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "userId", userId));

        if (this.cartRepository.existsByUser(user)) {
            CartDto cartDto = this.cartServiceImpl.getCartForUser(userId);
            cart = this.modelMapper.map(cartDto, Cart.class);

        } else {

            CartDto cartDto = this.cartServiceImpl.createCartForUser(userId);

            cart = this.modelMapper.map(cartDto, Cart.class);
        }



        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        if (existingCartItem.isPresent()) {

            // if product is alrady in the cart  then we have to increase the quantity and price also
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            cartItemRepository.save(cartItem);
            return modelMapper.map(cartItem, CartItemDto.class);
        } else {
            // Add new product to cart
            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound("Product", "id", productId));
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice() * quantity);
            CartItem savedCartItem = cartItemRepository.save(cartItem);
            return modelMapper.map(savedCartItem, CartItemDto.class);
        }
    }

    @Override
    public CartItemDto updateCartItem(Long userId, Long cartItemId, Integer quantity) {


        // checking the cart Item
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFound("CartItem", "id", cartItemId));

        // making only user associated can update  the cart item
        if (!cartItem.getCart().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You are not allowed to  remove this item");
        }

        // Updating  the cart item's quantity and price
        Product product = cartItem.getProduct();
        cartItem.setQuantity(quantity);
        cartItem.setPrice(product.getPrice() * quantity);


        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return modelMapper.map(updatedCartItem, CartItemDto.class);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) {


        // logic get the cart , check user and then delet it
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFound("CartItem", "id", cartItemId));

        if (!cartItem.getCart().getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You are not allowed to  remove this item");
        }

        cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItemDto> getCartItems(Long userId) {


      // logic , get cart , retrive all cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFound("Cart", "userId", userId));

        // Retrieve all cart items for the user's cart
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());

        // Map each CartItem to a CartItemDto
        return cartItems.stream()
                .map(cartItem -> modelMapper.map(cartItem, CartItemDto.class))
                .collect(Collectors.toList());

    }


}
