package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserOrderItemsDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.UserOrder;
import io.bookbar.bookbarbackend.entities.UserOrderItems;
import io.bookbar.bookbarbackend.mapper.UserOrderItemsMapper;
import io.bookbar.bookbarbackend.repository.BookRepository;
import io.bookbar.bookbarbackend.repository.UserOrderItemsRepository;
import io.bookbar.bookbarbackend.repository.UserOrderRepository;
import io.bookbar.bookbarbackend.service.UserOrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserOrderItemsServiceImpl implements UserOrderItemsService {

    @Autowired
    private UserOrderItemsRepository userOrderItemsRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public UserOrderItemsDTO addUserOrderItem(Long orderId, Long bookId) {
        UserOrder order = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        UserOrderItems userOrderItem = new UserOrderItems();
        userOrderItem.setOrder(order);
        userOrderItem.setBook(book);

        UserOrderItems savedUserOrderItem = userOrderItemsRepository.save(userOrderItem);
        return UserOrderItemsMapper.toDTO(savedUserOrderItem);
    }

    @Override
    public UserOrderItemsDTO updateUserOrderItem(Long orderItemId, Long bookId) {
        UserOrderItems userOrderItem = userOrderItemsRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        userOrderItem.setBook(book);

        UserOrderItems updatedUserOrderItem = userOrderItemsRepository.save(userOrderItem);
        return UserOrderItemsMapper.toDTO(updatedUserOrderItem);
    }

    @Override
    public void removeUserOrderItem(Long orderItemId) {
        userOrderItemsRepository.deleteById(orderItemId);
    }

    @Override
    public List<UserOrderItemsDTO> getUserOrderItemsByOrderId(Long orderId) {
        List<UserOrderItems> userOrderItems = userOrderItemsRepository.findByOrderId(orderId);
        return userOrderItems.stream()
                .map(UserOrderItemsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserOrderItemsDTO getUserOrderItemById(Long orderItemId) {
        UserOrderItems userOrderItem = userOrderItemsRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        return UserOrderItemsMapper.toDTO(userOrderItem);
    }
}
