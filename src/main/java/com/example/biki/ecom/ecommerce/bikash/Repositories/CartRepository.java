package com.example.biki.ecom.ecommerce.bikash.Repositories;

import com.example.biki.ecom.ecommerce.bikash.Entities.Cart;
import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart,Long> {


    Optional<Cart> findByUserId(Long userId);


    boolean existsByUser(User user);

}
