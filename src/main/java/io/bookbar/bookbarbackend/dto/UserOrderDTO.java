package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserOrderDTO {

    private Long id;
    private LocalDateTime date;
    private BigDecimal totalPrice;
    private Long userId;
    private List<UserOrderItemsDTO> orderItems;
}
