package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;

import java.util.List;

public interface UserOrderService {
    UserOrderDTO createOrder(Long userId, UserOrderDTO userOrderDTO);
    UserOrderDTO getOrderById(Long orderId);
    List<UserOrderDTO> getOrdersByUserId(Long userId);
    UserOrderDTO updateOrder(Long orderId, UserOrderDTO userOrderDTO);
    void deleteOrder(Long orderId);
}
