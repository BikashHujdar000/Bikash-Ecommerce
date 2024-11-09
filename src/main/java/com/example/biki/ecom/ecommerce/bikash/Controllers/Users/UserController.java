package com.example.biki.ecom.ecommerce.bikash.Controllers.Users;

import com.example.biki.ecom.ecommerce.bikash.Dtos.UserDto;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ApiResponse;
import com.example.biki.ecom.ecommerce.bikash.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/auth")

public class UserController {

    @Autowired
    private UserService userService;

    // getAllUsers
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = this.userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //deleteUsers

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteuser(@PathVariable("userId") Long userId) {

        this.userService.deleteUser(userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deleted Successfully");
        apiResponse.setSuccess(true);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    // updateUsers
    @PutMapping("/update/{userId}")
    public  ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable ("userId") Long userId)
    {
         UserDto user = this.userService.updateUser(userDto,userId);

         return  new ResponseEntity<>(user,HttpStatus.CREATED);

    }

    // get By Id

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {

        UserDto user = this.userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


}
