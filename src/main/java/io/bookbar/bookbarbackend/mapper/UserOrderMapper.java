package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.entities.UserOrder;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;
import io.bookbar.bookbarbackend.entities.UserOrderItems;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserOrderMapper {
    public static UserOrderDTO toUserOrderDTO(UserOrder userOrder) {
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setId(userOrder.getId());
        userOrderDTO.setDate(userOrder.getDate());
        userOrderDTO.setTotalPrice(userOrder.getTotalPrice());
        userOrderDTO.setOrderStatus(userOrder.getOrderStatus());
        userOrderDTO.setUserId(userOrder.getUser().getId());
        userOrderDTO.setOrderItems(userOrder.getOrderItems() != null ?
                userOrder.getOrderItems().stream()
                        .map(UserOrderItemsMapper::toDTO)
                        .collect(Collectors.toList()) :
                        new ArrayList<>());
        return userOrderDTO;
    }

    public static UserOrder toUserOrderEntity(UserOrderDTO userOrderDTO, User user, List<UserOrderItems> orderItems) {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(userOrderDTO.getId());
        userOrder.setDate(userOrderDTO.getDate());
        userOrder.setTotalPrice(userOrderDTO.getTotalPrice());
        userOrder.setOrderStatus(userOrderDTO.getOrderStatus());
        userOrder.setUser(user);
        userOrder.setOrderItems(orderItems != null ? orderItems : new ArrayList<>());
        return userOrder;
    }
}
