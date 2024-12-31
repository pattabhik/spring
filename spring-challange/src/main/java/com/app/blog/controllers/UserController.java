package com.app.blog.controllers;

import com.app.blog.dto.LoginDto;
import com.app.blog.dto.RegisterUserDTO;
import com.app.blog.models.Users;
import com.app.blog.repository.UserRepository;
import com.app.blog.util.EntitiyHawk;
import com.app.blog.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author 1460344
 */
@RestController
@RequestMapping("/")
public class UserController extends EntitiyHawk {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterUserDTO registerUserDTO, BindingResult result) {
        Object response = "User Registered";
        if (result.hasErrors()) {
            response = result.getFieldError().getField() + " " + result.getFieldError().getDefaultMessage();
        }else {
            Users user = new Users();
            user.setUserName(registerUserDTO.getName());
            user.setPassword(registerUserDTO.getPassword());
            user.setEmail(registerUserDTO.getEmail());
            userRepository.save(user);
        }
        return genericSuccess(response);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto, BindingResult result) {
        Object response = null;
        Optional<Users> user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (result.hasErrors() || user==null || !user.isPresent()) {
            response = "Invalid Username or Password";
        }else {
            response = jwtUtils.CreateJWTToken(user.orElse(null));
        }
        return genericSuccess(response);
    }
}
