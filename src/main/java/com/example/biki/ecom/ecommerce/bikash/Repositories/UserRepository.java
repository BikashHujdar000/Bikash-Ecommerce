package com.example.biki.ecom.ecommerce.bikash.Repositories;


import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
