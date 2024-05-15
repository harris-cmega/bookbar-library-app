package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.UserOrderDto;
import io.bookbar.bookbarbackend.entities.UserOrder;

public class UserOrderMapper {
    public static UserOrderDto maptoUserOrderDto(UserOrder userOrder){
        return new UserOrderDto(
                userOrder.getId(),
                userOrder.getDate(),
                userOrder.getTotalPrice(),
                userOrder.getUserID()
        );
    }

    public static UserOrder maptoUserOrder(UserOrderDto userOrderDto){
        return new UserOrder(
                userOrderDto.getId(),
                userOrderDto.getDate(),
                userOrderDto.getTotalPrice(),
                userOrderDto.getUserID()
        );
    }
}
