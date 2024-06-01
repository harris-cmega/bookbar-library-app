package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Long cartId;
    private Long bookId;
}
