package com.example.carVatika.dto;

public class UpdateCartItemRequestDto {

    private Long productId;
    private int quantity;

    public UpdateCartItemRequestDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
