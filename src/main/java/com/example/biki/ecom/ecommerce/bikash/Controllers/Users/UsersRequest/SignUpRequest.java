package com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {


    private String name;
    private String email;
    private String password;


}
