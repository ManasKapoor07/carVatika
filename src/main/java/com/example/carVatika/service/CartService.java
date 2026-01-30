package com.example.carVatika.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.carVatika.Repositories.Cartitemrepositories;
import com.example.carVatika.Repositories.Cartrepositories;
import com.example.carVatika.Repositories.ProducRepositories;
import com.example.carVatika.Repositories.UserRepositories;
import com.example.carVatika.dto.CartItemResponseDto;
import com.example.carVatika.dto.CartResponseDto;
import com.example.carVatika.dto.MergeCartItemDto;
import com.example.carVatika.model.Cart;
import com.example.carVatika.model.CartItem;
import com.example.carVatika.model.Product;
import com.example.carVatika.model.User;

@Service
@Transactional
public class CartService {

    private final Cartrepositories cartRepository;
    private final Cartitemrepositories cartItemRepository;
    private final ProducRepositories productRepository;
    private final UserRepositories userRepository;

    public CartService(
            Cartrepositories cartRepository,
            Cartitemrepositories cartItemRepository,
            ProducRepositories productRepository,
            UserRepositories userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public CartResponseDto getCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        return mapToDto(cart);
    }

    public CartResponseDto addToCart(Long userId, Long productId, int quantity) {

        Cart cart = getOrCreateCart(userId);

        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(
                cart.getId(), productId);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            CartItem newItem = new CartItem(product, quantity);

            // ✅ IMPORTANT: maintain both sides
            cart.addItem(newItem);
        }

        return mapToDto(cart);
    }

    public CartResponseDto updateQuantity(Long userId, Long productId, int quantity) {

        Cart cart = getOrCreateCart(userId);

        CartItem item = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (quantity <= 0) {
            // ✅ remove correctly
            cart.removeItem(item);
        } else {
            item.setQuantity(quantity);
        }

        return mapToDto(cart);
    }

    public CartResponseDto removeFromCart(Long userId, Long productId) {

        Cart cart = getOrCreateCart(userId);

        CartItem item = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // ✅ CRITICAL FIX
        cart.removeItem(item);

        return mapToDto(cart);
    }

    // =========================
    // HELPERS
    // =========================
    private Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));
    }

    private Cart createCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = new Cart(user);
        return cartRepository.save(cart);
    }

    // =========================
    // ENTITY → DTO MAPPING
    // =========================
    private CartResponseDto mapToDto(Cart cart) {

        List<CartItemResponseDto> items = cart.getCartItems()
                .stream()
                .map(item -> new CartItemResponseDto(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        item.getProduct()
                                .getImages()
                                .stream()
                                .map(img -> img.getImageUrl())
                                .toList()))
                .toList();

        double totalAmount = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        return new CartResponseDto(
                cart.getId(),
                items,
                totalAmount);
    }

    public CartResponseDto mergeGuestCart(Long userId, List<MergeCartItemDto> items) {

        Cart cart = getOrCreateCart(userId);

        for (MergeCartItemDto guestItem : items) {

            Long productId = guestItem.getProductId();
            int quantity = guestItem.getQuantity();

            if (quantity <= 0)
                continue;

            Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(
                    cart.getId(), productId);

            if (existingItem.isPresent()) {
                CartItem item = existingItem.get();
                item.setQuantity(item.getQuantity() + quantity);
            } else {
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                CartItem newItem = new CartItem(product, quantity);
                cart.addItem(newItem); // ✅ maintains both sides
            }
        }

        return mapToDto(cart);
    }

}
