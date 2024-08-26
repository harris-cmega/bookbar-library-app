package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.UserOrderItemsDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.UserOrder;
import io.bookbar.bookbarbackend.entities.UserOrderItems;

public class UserOrderItemsMapper {
    public static UserOrderItemsDTO toDTO(UserOrderItems entity) {
        UserOrderItemsDTO dto = new UserOrderItemsDTO();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrder().getId());
        dto.setBookId(entity.getBook().getId());
        return dto;
    }

    public static UserOrderItems toEntity(UserOrderItemsDTO dto, UserOrder order, Book book) {
        UserOrderItems entity = new UserOrderItems();
        entity.setId(dto.getId());
        entity.setOrder(order);
        entity.setBook(book);
        return entity;
    }
}
