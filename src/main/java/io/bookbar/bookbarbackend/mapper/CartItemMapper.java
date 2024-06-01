package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.CartItemDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.Cart;
import io.bookbar.bookbarbackend.entities.CartItem;

public class CartItemMapper {

    public static CartItemDTO toCartItemDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setCartId(cartItem.getCart().getId());
        dto.setBookId(cartItem.getBook().getId());
        return dto;
    }

    public static CartItem toCartItem(CartItemDTO cartItemDTO, Cart cart, Book book) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        cartItem.setCart(cart);
        cartItem.setBook(book);
        return cartItem;
    }
}
