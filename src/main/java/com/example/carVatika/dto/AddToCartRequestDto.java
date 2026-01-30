package com.example.carVatika.dto;

public class AddToCartRequestDto {

    private Long productId;
    private int quantity;

    public AddToCartRequestDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
