package com.example.biki.ecom.ecommerce.bikash.Controllers.Users;

import com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest.SignUpRequest;
import com.example.biki.ecom.ecommerce.bikash.Dtos.UserDto;
import com.example.biki.ecom.ecommerce.bikash.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUpUser( @Valid  @RequestBody SignUpRequest request){

           UserDto userResponse=  this.userService.signUpUser(request);

           return  new ResponseEntity<>(userResponse, HttpStatus.CREATED);

    }


}
