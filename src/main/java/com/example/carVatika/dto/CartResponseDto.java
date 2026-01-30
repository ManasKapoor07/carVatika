package com.example.carVatika.dto;

import java.util.List;

public class CartResponseDto {

    private Long cartId;
    private List<CartItemResponseDto> items;
    private double totalAmount;

    public CartResponseDto(
            Long cartId,
            List<CartItemResponseDto> items,
            double totalAmount) {

        this.cartId = cartId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<CartItemResponseDto> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
