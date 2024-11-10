package com.example.biki.ecom.ecommerce.bikash.Controllers.Admins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationAdminDto {
    private String email;
    private String password;
    private String name;
    private String role;
}
