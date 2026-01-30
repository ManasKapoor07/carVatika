package com.example.carVatika.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carVatika.model.CartItem;

public interface Cartitemrepositories extends JpaRepository<CartItem, Long> {
     Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

}
