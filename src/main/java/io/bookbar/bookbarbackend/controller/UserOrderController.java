package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.entities.UserOrder;
import io.bookbar.bookbarbackend.service.IUserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userOrders")
@RequiredArgsConstructor
public class UserOrderController {
    private final IUserOrderService userOrderService;

    @GetMapping
    public ResponseEntity<List<UserOrder>> getAllUserOrders(){
        return new ResponseEntity<>(userOrderService.getAllUserOrders(), HttpStatus.FOUND);
    }

    @PostMapping
    public UserOrder createUserOrder(@RequestBody UserOrder userOrder){
        return userOrderService.createUserOrder(userOrder);
    }

    @PutMapping("/update")
    public UserOrder updateUserOrder(@RequestBody UserOrder userOrder){
        return userOrderService.updateUserOrder(userOrder);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserOrder(@PathVariable Long id){
        userOrderService.deleteUserOrder(id);
    }

    @GetMapping("/userOrder/{id}")
    public UserOrder getUserOrderById(@PathVariable Long id){
        return userOrderService.getUserOrderById(id);
    }


}
