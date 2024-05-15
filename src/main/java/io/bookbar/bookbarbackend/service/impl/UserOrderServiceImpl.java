package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserOrderDto;
import io.bookbar.bookbarbackend.entities.UserOrder;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.UserOrderMapper;
import io.bookbar.bookbarbackend.repository.UserOrderRepository;
import io.bookbar.bookbarbackend.service.UserOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {
    private UserOrderRepository userOrderRepository;

    @Override
    public UserOrderDto createUserOrder(UserOrderDto userOrderDto) {
        UserOrder userOrder = UserOrderMapper.maptoUserOrder(userOrderDto);
        UserOrder savedUserOrder = userOrderRepository.save(userOrder);
        return UserOrderMapper.maptoUserOrderDto(savedUserOrder);
    }

    @Override
    public UserOrderDto getUserOrderById(Long id) {
        UserOrder userOrder = userOrderRepository.findById(id)
                .orElseThrow(()  -> new ResourceNotFoundException("User order is not exist."));
        return UserOrderMapper.maptoUserOrderDto(userOrder);
    }

    @Override
    public List<UserOrderDto> getAllUserOrders() {
        List<UserOrder> userOrders = userOrderRepository.findAll();
        return userOrders.stream().map((userOrder) -> UserOrderMapper.maptoUserOrderDto(userOrder))
                .collect(Collectors.toList());
    }

    @Override
    public UserOrderDto updateUserOrder(Long id, UserOrderDto updatedUserOrder) {
        UserOrder userOrder = userOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User order with given id does not exist!")
        );

        userOrder.setDate(updatedUserOrder.getDate());
        userOrder.setTotalPrice(updatedUserOrder.getTotalPrice());
        userOrder.setUserID(updatedUserOrder.getUserID());

        UserOrder updatedUserOrderObj = userOrderRepository.save(userOrder);

        return UserOrderMapper.maptoUserOrderDto(updatedUserOrderObj);
    }

    @Override
    public void deleteUserOrder(Long id) {
        UserOrder userOrder = userOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User order with given id does not exist!")
        );

        userOrderRepository.deleteById(id);
    }


}
