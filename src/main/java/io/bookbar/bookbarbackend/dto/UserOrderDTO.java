package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserOrderDTO {
    private Long id;

    @NotNull(message = "Date is mandatory")
    private LocalDateTime date;

    @NotNull(message = "Total price is mandatory")
    private BigDecimal totalPrice;

    @NotNull(message = "User ID is mandatory")
    private Long userId;
}
