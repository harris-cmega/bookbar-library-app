package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.entities.UserOrder;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.UserOrderMapper;
import io.bookbar.bookbarbackend.repository.UserOrderRepository;
import io.bookbar.bookbarbackend.repository.UserRepository;
import io.bookbar.bookbarbackend.service.UserOrderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {

    private static final Logger log = LoggerFactory.getLogger(UserOrderServiceImpl.class);

    private final UserOrderRepository userOrderRepository;
    private final UserRepository userRepository;

    @Override
    public UserOrderDTO createOrder(Long userId, UserOrderDTO userOrderDTO) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> {
                log.error("User not found with ID: {}", userId);
                return new ResourceNotFoundException("User not found");
            });

            UserOrder userOrder = new UserOrder();
            userOrder.setUser(user);


            UserOrder savedOrder = userOrderRepository.save(userOrder);
            return UserOrderMapper.toUserOrderDTO(savedOrder);
        } catch (Exception e) {
            log.error("Error creating order for user with ID: {}", userId, e);
            throw e;
        }
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
        UserOrder existingOrder = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        existingOrder.setDate(userOrderDTO.getDate());
        existingOrder.setTotalPrice(userOrderDTO.getTotalPrice());

        UserOrder updatedOrder = userOrderRepository.save(existingOrder);

        return UserOrderMapper.toUserOrderDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        UserOrder userOrder = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        userOrderRepository.delete(userOrder);
    }
}
