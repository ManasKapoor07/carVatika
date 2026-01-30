package com.example.carVatika.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carVatika.model.Cart;

public interface Cartrepositories extends JpaRepository<Cart, Long> {
        Optional<Cart> findByUserId(Long userId);

}
