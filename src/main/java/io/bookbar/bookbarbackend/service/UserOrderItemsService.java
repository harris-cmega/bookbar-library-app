package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.UserOrderItemsDTO;

import java.util.List;

public interface UserOrderItemsService {

    UserOrderItemsDTO addUserOrderItem(Long orderId, Long bookId);

    UserOrderItemsDTO updateUserOrderItem(Long orderItemId, Long bookId);

    void removeUserOrderItem(Long orderItemId);

    List<UserOrderItemsDTO> getUserOrderItemsByOrderId(Long orderId);

    UserOrderItemsDTO getUserOrderItemById(Long orderItemId);
}
