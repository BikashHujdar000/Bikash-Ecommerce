package com.example.biki.ecom.ecommerce.bikash.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String>home()
    {
         String welcome = " Welcome to Bikash Api Ecommerce Page";
         return  new ResponseEntity<>(welcome, HttpStatus.OK);
    }
}


