package com.example.biki.ecom.ecommerce.bikash.Controllers.Users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class FilterTestController {


    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
        String welcome = " Authorized Excess";
        return  new ResponseEntity<>(welcome, HttpStatus.OK);
    }
}


