package io.bookbar.bookbarbackend.dto;

import io.bookbar.bookbarbackend.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionDto {
    private Long id;
    private Long userId;
    private Long subscriptionId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
