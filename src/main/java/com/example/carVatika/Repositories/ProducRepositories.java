package com.example.carVatika.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carVatika.model.Product;

public interface ProducRepositories  extends JpaRepository<Product, Long >{
    
}
