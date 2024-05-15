package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.UserOrderDto;

import java.util.List;

public interface UserOrderService {
    UserOrderDto createUserOrder(UserOrderDto userOrderDto);

    UserOrderDto getUserOrderById(Long id);

    List<UserOrderDto> getAllUserOrders();

    UserOrderDto updateUserOrder(Long id, UserOrderDto updatedUserOrder);

    void deleteUserOrder(Long id);
}
