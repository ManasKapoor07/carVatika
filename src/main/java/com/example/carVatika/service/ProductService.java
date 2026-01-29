package com.example.carVatika.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.carVatika.Repositories.ProducRepositories;
import com.example.carVatika.model.Product;

@Service
public class ProductService  {
   private  ProducRepositories productRepository;

   //Contructor injection
   public ProductService(ProducRepositories productRepository){
    this.productRepository = productRepository;

   }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductsById(@PathVariable int id) {
        return  productRepository.findById(id).orElse(null);
    }
}
