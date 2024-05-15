package io.bookbar.bookbarbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDto {
    private long id;
    private LocalDateTime date;
    private Double totalPrice;
    private Long userID;
}
