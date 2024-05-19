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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {

    private final UserOrderRepository userOrderRepository;
    private final UserRepository userRepository;

    @Override
    public UserOrderDTO createUserOrder(UserOrderDTO userOrderDTO) {
        User user = userRepository.findById(userOrderDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserOrder userOrder = UserOrderMapper.toUserOrderEntity(userOrderDTO);
        userOrder.setUser(user);
        UserOrder savedUserOrder = userOrderRepository.save(userOrder);
        return UserOrderMapper.toUserOrderDTO(savedUserOrder);
    }

    @Override
    public UserOrderDTO getUserOrderById(Long id) {
        UserOrder userOrder = userOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User order not found"));
        return UserOrderMapper.toUserOrderDTO(userOrder);
    }

    @Override
    public List<UserOrderDTO> getAllUserOrders() {
        List<UserOrder> userOrders = userOrderRepository.findAll();
        return userOrders.stream()
                .map(UserOrderMapper::toUserOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserOrderDTO updateUserOrder(Long id, UserOrderDTO userOrderDTO) {
        UserOrder userOrder = userOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User order not found"));

        userOrder.setDate(userOrderDTO.getDate());
        userOrder.setTotalPrice(userOrderDTO.getTotalPrice());

        UserOrder updatedUserOrder = userOrderRepository.save(userOrder);
        return UserOrderMapper.toUserOrderDTO(updatedUserOrder);
    }

    @Override
    public void deleteUserOrder(Long id) {
        UserOrder userOrder = userOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User order not found"));
        userOrderRepository.delete(userOrder);
    }
}
