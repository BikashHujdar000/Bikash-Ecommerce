package com.example.biki.ecom.ecommerce.bikash.Repositories;

import com.example.biki.ecom.ecommerce.bikash.Entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    List<CartItem> findByCartId(Long cartId);


    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);


    void deleteByCartId(Long cartId);
}
