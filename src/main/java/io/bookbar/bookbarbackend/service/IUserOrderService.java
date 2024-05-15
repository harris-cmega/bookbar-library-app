package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.entities.UserOrder;

import java.util.List;

public interface IUserOrderService {
    UserOrder createUserOrder(UserOrder userOrder);
    List<UserOrder> getAllUserOrders();
    UserOrder updateUserOrder(UserOrder userOrder);
    UserOrder getUserOrderById(Long id);
    void deleteUserOrder(Long id);
}
