package com.experion.auth.controller;

import com.experion.auth.entity.Customer;
import com.experion.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<String> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Customer getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/createUser")
    public Customer createUser(@RequestBody Customer customer) {
        return userService.createUser(customer);
    }

    @PutMapping()
    public Customer updateUser(@RequestBody Customer customerDetails) {
        return userService.updateUser(customerDetails);
    }

    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
    }
}

