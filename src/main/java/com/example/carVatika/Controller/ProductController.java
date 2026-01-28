package com.example.carVatika.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.carVatika.model.Product;
import com.example.carVatika.service.ProductService;


@RestController
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping ("/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }

     @GetMapping ("/products/{id}")
    public Product getProductsById(@PathVariable int id) {
        return service.getProductsById(id);
    }
}
