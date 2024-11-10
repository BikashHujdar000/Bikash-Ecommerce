package com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl;

import com.example.biki.ecom.ecommerce.bikash.Controllers.Admins.RegistrationAdminDto;
import com.example.biki.ecom.ecommerce.bikash.Controllers.Users.UsersRequest.SignUpRequest;
import com.example.biki.ecom.ecommerce.bikash.Domain.USER_ROLE;
import com.example.biki.ecom.ecommerce.bikash.Dtos.UserDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ResourceNotFound;
import com.example.biki.ecom.ecommerce.bikash.Repositories.UserRepository;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDto signUpUser(SignUpRequest signUpRequest) {

        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());

        String hashedPassword = this.passwordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = this.userRepository.save(user);

        return this.modelMapper.map(savedUser, UserDto.class);

    }

    @Override
    public void deleteUser(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "userId", userId));

        this.userRepository.delete(user);

    }

    @Override
    public List<UserDto> getAllUsers() {


        List<User> users = this.userRepository.findAll();

        List<UserDto> responseUsers = users.stream()
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());


        return responseUsers;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "userId", userId));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());

        User updateduser = this.userRepository.save(user);

        return this.modelMapper.map(updateduser, UserDto.class);


    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "userId", userId));

        return this.modelMapper.map(user, UserDto.class);

    }

    @Override
    public UserDto createUserByAdmin(RegistrationAdminDto registrationAdminDto) {

        String hashedPassword = this.passwordEncoder.encode(registrationAdminDto.getPassword());
        User user = this.modelMapper.map(registrationAdminDto,User.class);
        user.setPassword(hashedPassword);

        try {
            USER_ROLE role = USER_ROLE.valueOf(registrationAdminDto.getRole());
            user.setRole(role);

        }catch ( Exception e)
        {
            throw new RuntimeException("Invalid role provided.");

        }
        // for testing
         log.info(" Show me the users to be saved :{}" ,user);


        User savedUser =  this.userRepository.save(user);


        log.info("  savedUsers :{}" ,savedUser);


        return  this.modelMapper.map(savedUser,UserDto.class);

    }


}
