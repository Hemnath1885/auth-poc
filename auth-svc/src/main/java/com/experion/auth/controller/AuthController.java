package com.experion.auth.controller;

import com.experion.auth.dto.AuthResponse;
import com.experion.auth.entity.Customer;
import com.experion.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody Customer customer) {
        String result =  userService.authenticateUser(customer);
        if (result.isEmpty()) return new ResponseEntity<>(new AuthResponse("unable to generate token"),HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(new AuthResponse(result,"created successfully"), HttpStatus.OK);
    }
}
