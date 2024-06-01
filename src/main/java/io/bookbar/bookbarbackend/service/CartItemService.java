package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.CartItemDTO;

import java.util.List;

public interface CartItemService {
    CartItemDTO addCartItem(Long cartId, Long bookId);
    CartItemDTO updateCartItem(Long cartItemId, Long bookId);
    void removeCartItem(Long cartItemId);
    List<CartItemDTO> getCartItemsByCartId(Long cartId);
    CartItemDTO getCartItemById(Long cartItemId);
}
