package com.example.biki.ecom.ecommerce.bikash.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<CartItem> cartItemsList= new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private  User user ;


}
