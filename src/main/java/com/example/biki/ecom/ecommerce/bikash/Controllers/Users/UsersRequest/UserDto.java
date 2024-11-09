package com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest;

import com.example.biki.ecom.ecommerce.bikash.Domain.USER_ROLE;
import jakarta.persistence.Column;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private USER_ROLE role;

}
