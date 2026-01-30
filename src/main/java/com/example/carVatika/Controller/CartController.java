package com.example.carVatika.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carVatika.dto.AddToCartRequestDto;
import com.example.carVatika.dto.CartResponseDto;
import com.example.carVatika.dto.MergeCartRequestDto;
import com.example.carVatika.dto.UpdateCartItemRequestDto;
import com.example.carVatika.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // 🔹 Get logged-in user's cart
    @GetMapping("/get-items")
    public CartResponseDto getCart(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return cartService.getCart(userId);
    }

    // 🔹 Add item to cart
    @PostMapping("/add")
    public CartResponseDto addToCart(
            Authentication authentication,
            @RequestBody AddToCartRequestDto request) {

        Long userId = (Long) authentication.getPrincipal();
        return cartService.addToCart(
                userId,
                request.getProductId(),
                request.getQuantity());
    }

    // 🔹 Update item quantity
    @PutMapping("/update")
    public CartResponseDto updateCartItem(
            Authentication authentication,
            @RequestBody UpdateCartItemRequestDto request) {

        Long userId = (Long) authentication.getPrincipal();
        return cartService.updateQuantity(
                userId,
                request.getProductId(),
                request.getQuantity());
    }

    // 🔹 Remove item from cart
    @DeleteMapping("/remove/{productId}")
    public CartResponseDto removeFromCart(
            Authentication authentication,
            @PathVariable Long productId) {

        Long userId = (Long) authentication.getPrincipal();
        System.out.println(userId);
        return cartService.removeFromCart(userId, productId);
    }

    @PostMapping("/merge")
    public CartResponseDto mergeCart(
            Authentication authentication,
            @RequestBody MergeCartRequestDto request) {

        Long userId = (Long) authentication.getPrincipal();
        return cartService.mergeGuestCart(userId, request.getItems());
    }

}
