package com.example.biki.ecom.ecommerce.bikash.Controllers.Admins;


import com.example.biki.ecom.ecommerce.bikash.Dtos.UserDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.ProductService;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private  UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Welcome to Admin Dashboard!";
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerUser")
    public   ResponseEntity<UserDto> createUserByAdmin( @RequestBody RegistrationAdminDto registrationAdminDto)
    {

        UserDto response =  this.userService.createUserByAdmin(registrationAdminDto);
        return  new ResponseEntity<>(response,HttpStatus.CREATED);
    }








}




