
package com.example.biki.ecom.ecommerce.bikash.Entities;

import com.example.biki.ecom.ecommerce.bikash.Domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private USER_ROLE role = USER_ROLE.ROLE_SELLER;

   @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private  Cart cart ;


}
