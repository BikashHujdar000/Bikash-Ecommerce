package com.example.biki.ecom.ecommerce.bikash.Entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Cartitem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne
   @JoinColumn(name = "cart_id",nullable = false)
    private  Cart cart;

   @ManyToOne
   @JoinColumn(name = "product_id")
    private  Product product;


   private Integer  quantity;

   private  double price;


}
