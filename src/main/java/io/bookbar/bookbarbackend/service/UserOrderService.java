package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;

import java.util.List;

public interface UserOrderService {
    UserOrderDTO createUserOrder(UserOrderDTO userOrderDTO);
    UserOrderDTO getUserOrderById(Long id);
    List<UserOrderDTO> getAllUserOrders();
    UserOrderDTO updateUserOrder(Long id, UserOrderDTO userOrderDTO);
    void deleteUserOrder(Long id);
}
