package com.example.biki.ecom.ecommerce.bikash.Services.AllServices;

import com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest.SignUpRequest;
import com.example.biki.ecom.ecommerce.bikash.Dtos.UserDto;

import java.util.List;

public interface UserService {

    // create user

    UserDto signUpUser(SignUpRequest signUpRequest);

    // delete User

    void deleteUser(Long userId);


    // get all user

    List<UserDto> getAllUsers();

    // update user

    UserDto updateUser(UserDto userDto,Long userId);

    // get User buy ID

    UserDto getUserById(Long userId);

}
