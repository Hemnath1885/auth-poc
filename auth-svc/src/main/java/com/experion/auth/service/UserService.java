package com.experion.auth.service;

import com.experion.auth.security.JwtUtil;
import com.experion.auth.repository.UserRepository;
import com.experion.auth.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JwtUtil jwtUtil;

    public List<String> getAllUsers() {
        return userRepository.findAllNames();
    }

    public Customer getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String authenticateUser(Customer customer){
        Optional<Customer> user = userRepository.findByUsername(customer.getUsername());
        if (user.isPresent() && bcrypt.matches(customer.getPassword(), user.get().getPassword())){
            return jwtUtil.generateToken(customer.getUsername());
        }
        return null;
    }

    public Customer createUser(Customer customer) {
        customer.setPassword(bcrypt.encode(customer.getPassword()));
        return userRepository.save(customer);
    }

    public Customer updateUser(Customer customerDetails) {
        Customer customer = userRepository.findByUsername(customerDetails.getUsername()).orElse(null);
        if (customer != null) {
            customer.setUsername(customerDetails.getUsername());
            customer.setPassword(bcrypt.encode(customerDetails.getPassword()));
            return userRepository.save(customer);
        }
        return null;
    }

    public void deleteUser(String userName) {
        Customer c = userRepository.findByUsername(userName).orElse(null);
        if (c !=null) userRepository.deleteById(c.getId());
    }
}

