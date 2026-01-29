package com.example.carVatika.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carVatika.model.User;


public interface UserRepositories extends JpaRepository<User , Long> {
    Optional <User>  findByEmail(String email);
}
