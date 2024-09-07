package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.entities.UserOrder;
import io.bookbar.bookbarbackend.entities.UserOrderItems;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.UserOrderMapper;
import io.bookbar.bookbarbackend.repository.BookRepository;
import io.bookbar.bookbarbackend.repository.UserOrderItemsRepository;
import io.bookbar.bookbarbackend.repository.UserOrderRepository;
import io.bookbar.bookbarbackend.repository.UserRepository;
import io.bookbar.bookbarbackend.service.UserOrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserOrderServiceImpl implements UserOrderService {

    private static final Logger log = LoggerFactory.getLogger(UserOrderServiceImpl.class);

    private final UserOrderRepository userOrderRepository;
    private final UserRepository userRepository;
    private final UserOrderItemsRepository userOrderItemsRepository;
    private final BookRepository bookRepository;

    @Autowired
    private final UserOrderMapper userOrderMapper;

    @Override
    public UserOrderDTO createOrder(Long userId, BigDecimal totalPrice) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> {
                log.error("User not found with ID: {}", userId);
                return new ResourceNotFoundException("User not found");
            });

            UserOrder userOrder = new UserOrder();
            userOrder.setUser(user);
            userOrder.setTotalPrice(totalPrice);  // Ensure this is set

            log.debug("Creating order with total price: {}", totalPrice);

            UserOrder savedOrder = userOrderRepository.save(userOrder);
            return UserOrderMapper.toUserOrderDTO(savedOrder);
        } catch (Exception e) {
            log.error("Error creating order for user with ID: {}", userId, e);
            throw e;
        }
    }


    @Override
    @Transactional
    public void addItemsToOrder(Long orderId, List<Book> books) {
        UserOrder order = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        for (Book book : books) {
            UserOrderItems orderItem = new UserOrderItems();
            orderItem.setBook(book);
            orderItem.setOrder(order);

            order.addOrderItem(orderItem); // Manages the collection and setting the parent
        }

        userOrderRepository.save(order); // Save the order with the updated collection
    }

    @Override
    public List<UserOrderDTO> getAllOrders() {
        List<UserOrder> orders = userOrderRepository.findAll();
        return orders.stream().map(UserOrderMapper::toUserOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserOrderDTO getOrderById(Long orderId) {
        UserOrder userOrder = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        return UserOrderMapper.toUserOrderDTO(userOrder);
    }

    @Override
    public List<UserOrderDTO> getOrdersByUserId(Long userId) {
        List<UserOrder> userOrders = userOrderRepository.findByUserId(userId);
        return userOrders.stream()
                .map(UserOrderMapper::toUserOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserOrderDTO updateOrder(Long orderId, UserOrderDTO userOrderDTO) {
        // Fetch the existing order
        UserOrder existingOrder = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));

        // Update the fields from the DTO
        existingOrder.setOrderStatus(userOrderDTO.getOrderStatus());
        // Add other fields you want to update from the DTO
        // Note: We don't update 'order_date' as it's auto-generated and should remain unchanged

        // Save the updated order
        UserOrder updatedOrder = userOrderRepository.save(existingOrder);

        // Convert to DTO and return
        return userOrderMapper.toUserOrderDTO(updatedOrder);
    }


    @Override
    public void deleteOrder(Long orderId) {
        UserOrder userOrder = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        userOrderRepository.delete(userOrder);
    }
}
