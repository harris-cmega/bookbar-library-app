package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.UserOrder;

import java.math.BigDecimal;
import java.util.List;

public interface UserOrderService {
    UserOrderDTO createOrder(Long userId, BigDecimal totalPrice);
    UserOrderDTO getOrderById(Long orderId);
    List<UserOrderDTO> getOrdersByUserId(Long userId);
    UserOrderDTO updateOrder(Long orderId, UserOrderDTO userOrderDTO);
    void addItemsToOrder(Long orderId, List<Book> books);
    void deleteOrder(Long orderId);
    List<UserOrderDTO> getAllOrders();
}
