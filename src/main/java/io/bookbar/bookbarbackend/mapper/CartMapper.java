package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.CartDTO;
import io.bookbar.bookbarbackend.entities.Cart;
import io.bookbar.bookbarbackend.entities.CartItem;
import io.bookbar.bookbarbackend.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toCartDTO(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setCartItems(cart.getCartItems().stream()
                .map(CartItemMapper::toCartItemDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public static Cart toCartEntity(CartDTO dto, User user, List<CartItem> cartItems) {
        if (dto == null || user == null) {
            return null;
        }
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setUser(user);
        cart.setCartItems(cartItems);
        return cart;
    }
}
