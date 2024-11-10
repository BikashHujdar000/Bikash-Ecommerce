package com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl;


import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import com.example.biki.ecom.ecommerce.bikash.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserAuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

// first task get the  user from database

    User user = this.userRepository.findByEmail(email).orElseThrow( ()-> new UsernameNotFoundException("User not Found"));


        // now let's returen user details giving the user to User of SPring



        List<SimpleGrantedAuthority> authorityList =
                List.of(new SimpleGrantedAuthority((user.getRole().name())));


        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorityList);


    }
}


