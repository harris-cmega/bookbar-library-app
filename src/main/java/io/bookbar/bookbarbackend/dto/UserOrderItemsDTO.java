package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class UserOrderItemsDTO {

    private Long id;

    @NotNull(message = "Order ID is mandatory")
    private Long orderId;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;
}
