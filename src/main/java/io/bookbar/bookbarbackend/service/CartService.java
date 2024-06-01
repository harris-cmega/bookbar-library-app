package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.CartDTO;

import java.util.List;

public interface CartService {
    CartDTO createCart(Long userId);
    CartDTO getCartById(Long cartId);
    CartDTO getCartByUserId(Long userId);
    CartDTO addItemToCart(Long cartId, Long bookId);
    CartDTO removeItemFromCart(Long cartId, Long cartItemId);
    CartDTO clearCart(Long cartId);
    CartDTO updateCart(Long cartId, List<Long> bookIds);
    void deleteCart(Long cartId);
}
