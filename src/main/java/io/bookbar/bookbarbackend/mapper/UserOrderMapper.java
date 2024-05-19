package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;
import io.bookbar.bookbarbackend.entities.UserOrder;

public class UserOrderMapper {
    public static UserOrder toUserOrderEntity(UserOrderDTO userOrderDTO) {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(userOrderDTO.getId());
        userOrder.setDate(userOrderDTO.getDate());
        userOrder.setTotalPrice(userOrderDTO.getTotalPrice());
        return userOrder;
    }

    public static UserOrderDTO toUserOrderDTO(UserOrder userOrder) {
        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setId(userOrder.getId());
        userOrderDTO.setDate(userOrder.getDate());
        userOrderDTO.setTotalPrice(userOrder.getTotalPrice());
        userOrderDTO.setUserId(userOrder.getUser().getId());
        return userOrderDTO;
    }
}
