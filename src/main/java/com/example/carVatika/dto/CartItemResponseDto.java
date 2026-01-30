package com.example.carVatika.dto;

import java.util.List;

public class CartItemResponseDto {

    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private List<String> images;

    public CartItemResponseDto(
            Long productId,
            String productName,
            double price,
            int quantity,
            List<String> images) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.images = images;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<String> getImages() {
        return images;
    }
}
