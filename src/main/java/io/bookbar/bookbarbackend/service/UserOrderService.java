package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.entities.UserOrder;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.repository.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOrderService implements IUserOrderService{
    private final UserOrderRepository userOrderRepository;

    @Override
    public List<UserOrder> getAllUserOrders(){
        return userOrderRepository.findAll();
    }

    @Override
    public UserOrder createUserOrder(UserOrder userOrder){
        if (userOrderAlreadyExists(userOrder.getId())){
            throw new ResourceNotFoundException("User order with id: " + userOrder.getId() + " already exists!");
        }
        return userOrderRepository.save(userOrder);
    }

    @Override
    public UserOrder updateUserOrder(UserOrder userOrder){
        return userOrderRepository.save(userOrder);
    }

    @Override
    public UserOrder getUserOrderById(Long id){
        return userOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sorry, order with this id " + id + " not found!"));
    }

    @Override
    public void deleteUserOrder(Long id){
        if(!userOrderRepository.existsById(id)){
            throw new ResourceNotFoundException("Sorry, order not found!");
        }
    }

    private boolean userOrderAlreadyExists(Long id){
        return userOrderRepository.findById(id).isPresent();
    }
}
