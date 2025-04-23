package com.experion.auth.init;

import com.experion.auth.entity.Customer;
import com.experion.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Customer defaultCustomer = new Customer();
        defaultCustomer.setUsername("admin");
        defaultCustomer.setPassword("admin");

        userService.createUser(defaultCustomer);
        System.out.println("Default customer inserted");
    }
}
