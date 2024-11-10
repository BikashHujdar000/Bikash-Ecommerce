package com.example.biki.ecom.ecommerce.bikash.Controllers.Users;

import com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UserResponses.LoginResponse;
import com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest.LoginRequest;
import com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest.SignUpRequest;
import com.example.biki.ecom.ecommerce.bikash.Dtos.UserDto;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ApiResponse;
import com.example.biki.ecom.ecommerce.bikash.JWT.JwtUtils;
import com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl.UserAuthServiceImpl;
import com.example.biki.ecom.ecommerce.bikash.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthServiceImpl userAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUpUser(@Valid @RequestBody SignUpRequest request) {

        UserDto userResponse = this.userService.signUpUser(request);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

    }


    // lets create for login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        // authentication set

        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        } catch (AuthenticationException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        // okay not authentication manager have my login email and password

        UserDetails userDetails;

        try {
            userDetails = this.userAuthService.loadUserByUsername(loginRequest.getEmail());

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        // aab agar user milgaya toh kya kar vai tu nikal aab token

        String token = this.jwtUtils.generateToken(userDetails.getUsername());

        // now return what you want

        LoginResponse response = new LoginResponse();

        response.setJwtToken(token);


        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);


    }


}
