package com.example.biki.ecom.ecommerce.bikash.Services;

import com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest.SignUpRequest;
import com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest.UserDto;

import java.util.List;

public interface UserService {

    // create user

    UserDto signUpUser(SignUpRequest signUpRequest);

    // delete User

    void deleteUser(Integer userId);


    // get all user

    List<UserDto> getAllUsers();

    // update user

    UserDto updateUser(UserDto userDto, Integer userId);

    // get User buy ID

    UserDto getUserById(Integer userId);

}
