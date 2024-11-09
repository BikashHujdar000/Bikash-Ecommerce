package com.example.biki.ecom.ecommerce.bikash.Dtos;

import com.example.biki.ecom.ecommerce.bikash.Domain.USER_ROLE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {

    private Long id;


    @NotEmpty(message = "Name is required")
    private String name;

    @Email(message = "Enter a valid email")
    @Pattern(regexp = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,4}$", message = "Email format is invalid")
    private String email;


    private USER_ROLE role;

}
