package io.bookbar.bookbarbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long customerId;
}