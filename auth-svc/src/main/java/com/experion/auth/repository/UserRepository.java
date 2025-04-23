package com.experion.auth.repository;

import com.experion.auth.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);

    @Query("SELECT c.username FROM Customer c")
    List<String> findAllNames();
}

