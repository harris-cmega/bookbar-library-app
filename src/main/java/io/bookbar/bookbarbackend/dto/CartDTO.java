package io.bookbar.bookbarbackend.dto;

import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDTO {

    private Long id;
    private Long userId;
    private List<CartItemDTO> cartItems;
}
